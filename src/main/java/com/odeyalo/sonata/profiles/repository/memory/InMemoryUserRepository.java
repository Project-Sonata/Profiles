package com.odeyalo.sonata.profiles.repository.memory;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public final class InMemoryUserRepository implements UserRepository {
    private final ConcurrentMap<Long, UserEntity> cache = new ConcurrentHashMap<>();
    // Do not move it to separate interface such as IdGenerator to make code more simple.
    private final AtomicLong idCounter = new AtomicLong();

    public InMemoryUserRepository() {}

    public InMemoryUserRepository(UserEntity... entities) {
        // Do not copy code from saveAll method, just block it. Any way it is used as in-memory stuff and really fast.
        saveAll(Arrays.asList(entities)).blockLast();
    }

    public InMemoryUserRepository(final List<UserEntity> predefinedEntities) {
        saveAll(predefinedEntities).blockLast();
    }

    public static InMemoryUserRepository withPredefinedEntities(UserEntity... entities) {
        return new InMemoryUserRepository(entities);
    }

    public static InMemoryUserRepository withPredefinedEntities(final List<UserEntity> predefinedEntities) {
        return new InMemoryUserRepository(predefinedEntities);
    }

    @Override
    public Mono<UserEntity> findByPublicId(@NotNull final String publicId) {
        final Optional<UserEntity> maybeUser = cache.values().stream()
                .filter(it -> Objects.equals(publicId, it.getPublicId()))
                .findFirst();

        return Mono.justOrEmpty(maybeUser);
    }

    @Override
    public @NotNull Mono<UserEntity> findByPublicIdOrEmail(@NotNull final String publicId, @NotNull final String email) {
        return findByPublicId(publicId)
                .switchIfEmpty(Mono.defer(() -> {
                            final Optional<UserEntity> maybeUser = cache.values().stream()
                                    .filter(it -> Objects.equals(it.getEmail(), email))
                                    .findFirst();
                            return Mono.justOrEmpty(maybeUser);
                        })
                );

    }

    @Override
    public @NotNull <S extends UserEntity> Mono<S> save(@NotNull final S entity) {
        //noinspection DuplicatedCode
        return Mono.fromCallable(() -> {
            if (entity.getId() == null) {
                final long id = idCounter.incrementAndGet();
                // we know that the UserEntity is immutable and can't be extended. Safely cast it
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
    public @NotNull <S extends UserEntity> Flux<S> saveAll(@NotNull final Iterable<S> entities) {
        return Flux.fromIterable(entities)
                .concatMap(this::save);
    }

    @Override
    public @NotNull <S extends UserEntity> Flux<S> saveAll(@NotNull final Publisher<S> entityStream) {
        return Flux.from(entityStream)
                .concatMap(this::save);
    }

    @Override
    public @NotNull Mono<UserEntity> findById(@NotNull final Long id) {
        return Mono.fromCallable(() -> cache.get(id));
    }

    @Override
    public @NotNull Mono<UserEntity> findById(@NotNull final Publisher<Long> id) {
        return Mono.from(id)
                .flatMap(this::findById);
    }

    @Override
    public @NotNull Mono<Boolean> existsById(@NotNull final Long id) {
        return Mono.just(
                cache.containsKey(id)
        );
    }

    @Override
    public Mono<Boolean> existsById(@NotNull final Publisher<Long> id) {
        return Mono.from(id)
                .flatMap(this::existsById);
    }

    @Override
    public @NotNull Flux<UserEntity> findAll() {
        return Flux.fromIterable(
                cache.values()
        );
    }

    @Override
    public @NotNull Flux<UserEntity> findAllById(@NotNull final Iterable<Long> ids) {
        return Flux.fromIterable(ids)
                .concatMap(this::findById);
    }

    @Override
    public @NotNull Flux<UserEntity> findAllById(@NotNull final Publisher<Long> idStream) {
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
    public @NotNull Mono<Void> deleteById(@NotNull final Publisher<Long> id) {
        return Mono.from(id)
                .flatMap(this::deleteById);
    }

    @Override
    public @NotNull Mono<Void> delete(@NotNull final UserEntity entity) {
        return deleteById(entity.getId());
    }

    @Override
    public @NotNull Mono<Void> deleteAllById(@NotNull final Iterable<? extends Long> ids) {
        return Flux.fromIterable(ids)
                .flatMap(this::deleteById)
                .then();
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Iterable<? extends UserEntity> entities) {
        return Flux.fromIterable(entities)
                .map(UserEntity::getId)
                .flatMap(this::deleteById)
                .then();
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Publisher<? extends UserEntity> entityStream) {
        return Flux.from(entityStream)
                .map(UserEntity::getId)
                .flatMap(this::deleteById)
                .then();
    }

    @Override
    public @NotNull Mono<Void> deleteAll() {
        return Mono.fromRunnable(cache::clear);
    }
}
