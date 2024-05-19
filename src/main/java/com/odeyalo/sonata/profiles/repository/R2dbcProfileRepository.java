package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface R2dbcProfileRepository extends R2dbcRepository<UserProfileEntity, Long> {

    Mono<UserProfileEntity> findByPublicId(@NotNull String publicId);

}
