package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.repository.core.Repository;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

/**
 * Contract to work with {@link UserEntity}
 */
public interface UserRepository extends Repository<UserEntity, Long> {

    /**
     * Finds a {@code UserEntity} by its public ID.
     *
     * @param publicId the public ID of the user profile, must not be null
     * @return a {@code Mono} emitting the found {@code UserEntity}, or an empty {@code Mono} if no user profile is found
     */
    Mono<UserEntity> findByPublicId(@NotNull String publicId);

    /**
     * Finds a {@code UserEntity} by its public ID or email.
     *
     * @param publicId the public ID of the user profile, must not be null
     * @param email    the email of the user profile, must not be null
     * @return a {@code Mono} emitting the found {@code UserEntity}, or an empty {@code Mono} if no user profile is found
     */
    @NotNull
    Mono<UserEntity> findByPublicIdOrEmail(@NotNull String publicId, @NotNull String email);
}
