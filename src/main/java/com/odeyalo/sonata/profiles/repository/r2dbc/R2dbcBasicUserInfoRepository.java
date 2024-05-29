package com.odeyalo.sonata.profiles.repository.r2dbc;

import com.odeyalo.sonata.profiles.entity.BasicUserInfo;
import com.odeyalo.sonata.profiles.repository.BasicUserInfoRepository;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcBasicUserInfoRepositoryDelegate;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * R2dbc implementation of {@link BasicUserInfoRepository} that uses {@link R2dbcBasicUserInfoRepositoryDelegate} to perform all operations
 */
@Component
public final class R2dbcBasicUserInfoRepository implements BasicUserInfoRepository {
    private final R2dbcBasicUserInfoRepositoryDelegate delegate;

    public R2dbcBasicUserInfoRepository(final R2dbcBasicUserInfoRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public @NotNull <S extends BasicUserInfo> Mono<S> save(@NotNull final S entity) {
        return delegate.save(entity);
    }

    @Override
    public @NotNull <S extends BasicUserInfo> Flux<S> saveAll(@NotNull final Iterable<S> entities) {
        return delegate.saveAll(entities);
    }

    @Override
    public @NotNull <S extends BasicUserInfo> Flux<S> saveAll(@NotNull final Publisher<S> entityStream) {
        return delegate.saveAll(entityStream);
    }

    @Override
    public @NotNull Mono<BasicUserInfo> findById(@NotNull final Long id) {
        return delegate.findById(id);
    }

    @Override
    public @NotNull Mono<BasicUserInfo> findById(@NotNull final Publisher<Long> id) {
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
    public @NotNull Flux<BasicUserInfo> findAll() {
        return delegate.findAll();
    }

    @Override
    public @NotNull Flux<BasicUserInfo> findAllById(@NotNull final Iterable<Long> ids) {
        return delegate.findAllById(ids);
    }

    @Override
    public @NotNull Flux<BasicUserInfo> findAllById(@NotNull final Publisher<Long> idStream) {
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
    public @NotNull Mono<Void> delete(@NotNull final BasicUserInfo entity) {
        return delegate.delete(entity);
    }

    @Override
    public @NotNull Mono<Void> deleteAllById(@NotNull final Iterable<? extends Long> ids) {
        return delegate.deleteAllById(ids);
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Iterable<? extends BasicUserInfo> entities) {
        return delegate.deleteAll(entities);
    }

    @Override
    public @NotNull Mono<Void> deleteAll(@NotNull final Publisher<? extends BasicUserInfo> entityStream) {
        return delegate.deleteAll();
    }

    @Override
    public @NotNull Mono<Void> deleteAll() {
        return delegate.deleteAll();
    }
}
