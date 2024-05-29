package com.odeyalo.sonata.profiles.repository.r2dbc.delegate;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcUserRepositoryDelegate extends R2dbcRepository<UserEntity, Long> {

    Mono<UserEntity> findByPublicId(@NotNull String publicId);

    Mono<UserEntity> findByContextUri(@NotNull String contextUri);

}
