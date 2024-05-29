package com.odeyalo.sonata.profiles.repository.r2dbc;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.repository.UserRepository;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcUserRepositoryDelegate;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public final class R2dbcUserRepository implements UserRepository {
    private final R2dbcUserRepositoryDelegate delegate;

    public R2dbcUserRepository(final R2dbcUserRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Mono<UserEntity> findByPublicId(@NotNull final String publicId) {
        return delegate.findByPublicId(publicId);
    }

    @Override
    public @NotNull Mono<UserEntity> findByPublicIdOrEmail(@NotNull final String publicId, @NotNull final String email) {
        return delegate.findByPublicIdOrEmail(publicId, email);
    }

    @Override
    public @NotNull <S extends UserEntity> Mono<S> save(@NotNull final S entity) {
        return delegate.save(entity);
    }

    @Override
    public @NotNull <S extends UserEntity> Flux<S> saveAll(@NotNull final Iterable<S> entities) {
        return delegate.saveAll(entities);
    }

    @Override
    public @NotNull <S extends UserEntity> Flux<S> saveAll(@NotNull final Publisher<S> entityStream) {
        return delegate.saveAll(entityStream);
    }

    @Override
    public @NotNull Mono<UserEntity> findById(@NotNull final Long id) {
        return delegate.findById(id);
    }

    @Override
    public @NotNull Mono<UserEntity> findById(@NotNull final Publisher<Long> id) {
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
    public @NotNull Flux<UserEntity> findAll() {
        return delegate.findAll();
    }

    @Override
    public @NotNull Flux<UserEntity> findAllById(@NotNull final Iterable<Long> ids) {
        return delegate.findAllById(ids);
    }

    @Override
    public @NotNull Flux<UserEntity> findAllById(@NotNull final Publisher<Long> idStream) {
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
    public @NotNull Mono<Void> delete(@NotNull final UserEntity entity) {
        return delegate.delete(entity);
    }

    @Override
    public @NotNull Mono<Void> deleteAllById(@NotNull final Iterable<? extends Long> ids) {
        return delegate.deleteAllById(ids);
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Iterable<? extends UserEntity> entities) {
        return delegate.deleteAll(entities);
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Publisher<? extends UserEntity> entityStream) {
        return delegate.deleteAll(entityStream);
    }

    @Override
    public @NotNull Mono<Void> deleteAll() {
        return delegate.deleteAll();
    }
}
