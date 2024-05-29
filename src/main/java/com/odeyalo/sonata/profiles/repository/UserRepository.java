package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.repository.core.Repository;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public interface UserRepository extends Repository<UserEntity, Long> {

    Mono<UserEntity> findByPublicId(@NotNull String publicId);

    Mono<UserEntity> findByContextUri(@NotNull String contextUri);

}
