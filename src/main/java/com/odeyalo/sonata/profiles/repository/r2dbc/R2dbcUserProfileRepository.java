package com.odeyalo.sonata.profiles.repository.r2dbc;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.UserProfileRepository;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcProfileRepositoryDelegate;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of {@link UserProfileRepository} that uses R2DBC to work with database
 *
 * @see UserProfileRepository
 */
@Component
public final class R2dbcUserProfileRepository implements UserProfileRepository {
    private final R2dbcProfileRepositoryDelegate delegate;

    public R2dbcUserProfileRepository(final R2dbcProfileRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public @NotNull Mono<UserProfileEntity> findByUserId(@NotNull final Long userId) {
        return delegate.findByUserId(userId);
    }

    @Override
    public @NotNull <S extends UserProfileEntity> Mono<S> save(@NotNull final S entity) {
        return delegate.save(entity);
    }

    @Override
    public @NotNull <S extends UserProfileEntity> Flux<S> saveAll(@NotNull final Iterable<S> entities) {
        return delegate.saveAll(entities);
    }

    @Override
    public @NotNull <S extends UserProfileEntity> Flux<S> saveAll(@NotNull final Publisher<S> entityStream) {
        return delegate.saveAll(entityStream);
    }

    @Override
    public @NotNull Mono<UserProfileEntity> findById(@NotNull final Long id) {
        return delegate.findById(id);
    }

    @Override
    public @NotNull Mono<UserProfileEntity> findById(@NotNull final Publisher<Long> id) {
        return delegate.findById(id);
    }

    @Override
    public @NotNull Mono<Boolean> existsById(@NotNull final Long id) {
        return delegate.existsById(id);
    }

    @Override
    public Mono<Boolean> existsById(@NotNull final Publisher<Long> id) {
        return delegate.existsById(id);
    }

    @Override
    public @NotNull Flux<UserProfileEntity> findAll() {
        return delegate.findAll();
    }

    @Override
    public @NotNull Flux<UserProfileEntity> findAllById(@NotNull final Iterable<Long> ids) {
        return delegate.findAllById(ids);
    }

    @Override
    public @NotNull Flux<UserProfileEntity> findAllById(@NotNull final Publisher<Long> idStream) {
        return delegate.findAllById(idStream);
    }

    @Override
    public @NotNull Mono<Long> count() {
        return delegate.count();
    }

    @Override
    public @NotNull Mono<Void> deleteById(@NotNull final Long id) {
        return delegate.deleteById(id);
    }

    @Override
    public @NotNull Mono<Void> deleteById(@NotNull final Publisher<Long> id) {
        return delegate.deleteById(id);
    }

    @Override
    public @NotNull Mono<Void> delete(@NotNull final UserProfileEntity entity) {
        return delegate.delete(entity);
    }

    @Override
    public @NotNull Mono<Void> deleteAllById(@NotNull final Iterable<? extends Long> ids) {
        return delegate.deleteAllById(ids);
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Iterable<? extends UserProfileEntity> entities) {
        return delegate.deleteAll(entities);
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Publisher<? extends UserProfileEntity> entityStream) {
        return delegate.deleteAll(entityStream);
    }

    @Override
    public @NotNull Mono<Void> deleteAll() {
        return delegate.deleteAll();
    }
}
