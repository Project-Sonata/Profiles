package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.core.Repository;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

/**
 * Entity-specific repository that used to work ONLY with {@link UserProfileEntity}
 * and provide useful methods to fetch, update and save data about it.
 */
public interface UserProfileRepository extends Repository<UserProfileEntity, Long> {

    /**
     * Search for the user in repository by user's public ID(the ID that used by external APIs)
     * @param publicId public ID of the user, not null
     * @return a {@link Mono} emitting {@link UserProfileEntity} if was found, or empty {@link Mono} if the entity with the given public ID does not exist
     */
    @NotNull
    Mono<UserProfileEntity> findByPublicId(@NotNull String publicId);

    /**
     * Finds a {@code UserProfileEntity} by its public ID or email.
     *
     * @param publicId the public ID of the user profile, must not be  ull
     * @param email    the email of the user profile, must not be null
     * @return a {@code Mono} emitting the found {@code UserProfileEntity}, or an empty {@code Mono} if no user profile is found
     */
    @NotNull
    Mono<UserProfileEntity> findByPublicIdOrEmail(@NotNull String publicId, @NotNull String email);
}
