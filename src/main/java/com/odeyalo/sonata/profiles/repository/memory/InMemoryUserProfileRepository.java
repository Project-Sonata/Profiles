package com.odeyalo.sonata.profiles.repository.memory;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.UserProfileRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public final class InMemoryUserProfileRepository implements UserProfileRepository {
    private final ConcurrentMap<Long, UserProfileEntity> cache = new ConcurrentHashMap<>();
    // Do not move it to separate interface such as IdGenerator to make code more simple.
    private final AtomicLong idCounter = new AtomicLong();

    public InMemoryUserProfileRepository() {}

    public InMemoryUserProfileRepository(UserProfileEntity... entities) {
        // Do not copy code from saveAll method, just block it. Any way it is used as in-memory stuff and really fast.
        saveAll(Arrays.asList(entities)).blockLast();
    }

    public InMemoryUserProfileRepository(final List<UserProfileEntity> predefinedEntities) {
        saveAll(predefinedEntities).blockLast();
    }

    public static InMemoryUserProfileRepository withPredefinedEntities(UserProfileEntity... entities) {
        return new InMemoryUserProfileRepository(entities);
    }

    public static InMemoryUserProfileRepository withPredefinedEntities(final List<UserProfileEntity> predefinedEntities) {
        return new InMemoryUserProfileRepository(predefinedEntities);
    }

    @Override
    public @NotNull Mono<UserProfileEntity> findByPublicId(@NotNull final String publicId) {
        return Flux.fromStream(cache.entrySet().stream())
                .map(Map.Entry::getValue)
                .filter(it -> Objects.equals(it.getPublicId(), publicId))
                .next();
    }

    @Override
    public @NotNull Mono<UserProfileEntity> findByPublicIdOrEmail(@NotNull final String publicId, @NotNull final String email) {
        return findByPublicId(publicId)
                .switchIfEmpty(Mono.defer(() -> {
                            Optional<UserProfileEntity> maybeUser = cache.values().stream()
                                    .filter(it -> Objects.equals(it.getEmail(), email))
                                    .findFirst();
                            return Mono.justOrEmpty(maybeUser);
                        })
                );

    }

    @Override
    public @NotNull <S extends UserProfileEntity> Mono<S> save(@NotNull final S entity) {
        return Mono.fromCallable(() -> {
            if (entity.getId() == null) {
                final long id = idCounter.incrementAndGet();
                // we know that the UserProfileEntity is immutable and can't be extended. Safely cast it
                @SuppressWarnings("unchecked")
                S toSave = (S) entity.withId(id);

                cache.put(id, toSave);

                return toSave;
            }

            cache.put(entity.getId(), entity);
            return entity;
        });
    }

    @Override
    public @NotNull <S extends UserProfileEntity> Flux<S> saveAll(@NotNull final Iterable<S> entities) {
        return Flux.fromIterable(entities)
                .concatMap(this::save);
    }

    @Override
    public @NotNull <S extends UserProfileEntity> Flux<S> saveAll(@NotNull final Publisher<S> entityStream) {
        return Flux.from(entityStream)
                .concatMap(this::save);
    }

    @Override
    public @NotNull Mono<UserProfileEntity> findById(@NotNull final Long id) {
        return Mono.fromCallable(() -> cache.get(id));
    }

    @Override
    public @NotNull Mono<UserProfileEntity> findById(@NotNull final Publisher<Long> idPublisher) {
        return Mono.from(idPublisher)
                .flatMap(this::findById);
    }

    @Override
    public @NotNull Mono<Boolean> existsById(@NotNull final Long id) {
        return Mono.just(
                cache.containsKey(id)
        );
    }

    @Override
    public Mono<Boolean> existsById(@NotNull final Publisher<Long> idPublisher) {
        return Mono.from(idPublisher)
                .flatMap(this::existsById);
    }

    @Override
    public @NotNull Flux<UserProfileEntity> findAll() {
        return Flux.fromIterable(
                cache.values()
        );
    }

    @Override
    public @NotNull Flux<UserProfileEntity> findAllById(@NotNull final Iterable<Long> ids) {
        return Flux.fromIterable(ids)
                .concatMap(this::findById);
    }

    @Override
    public @NotNull Flux<UserProfileEntity> findAllById(@NotNull final Publisher<Long> idStream) {
        return Flux.from(idStream)
                .concatMap(this::findById);
    }

    @Override
    public @NotNull Mono<Long> count() {
        return Mono.just(
                (long) cache.size()
        );
    }

    @Override
    public @NotNull Mono<Void> deleteById(@NotNull final Long id) {
        return Mono.fromRunnable(() -> cache.remove(id));
    }

    @Override
    public @NotNull Mono<Void> deleteById(@NotNull final Publisher<Long> idPublisher) {
        return Mono.from(idPublisher)
                .flatMap(this::deleteById);
    }

    @Override
    public @NotNull Mono<Void> delete(@NotNull final UserProfileEntity entity) {
        return deleteById(entity.getId());
    }

    @Override
    public @NotNull Mono<Void> deleteAllById(@NotNull final Iterable<? extends Long> ids) {
        return Flux.fromIterable(ids)
                .flatMap(this::deleteById)
                .then();
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Iterable<? extends UserProfileEntity> entities) {
        return Flux.fromIterable(entities)
                .map(UserProfileEntity::getId)
                .flatMap(this::deleteById)
                .then();
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Publisher<? extends UserProfileEntity> entityStream) {
        return Flux.from(entityStream)
                .map(UserProfileEntity::getId)
                .flatMap(this::deleteById)
                .then();
    }

    @Override
    public @NotNull Mono<Void> deleteAll() {
        return Mono.fromRunnable(cache::clear);
    }
}
