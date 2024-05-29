package com.odeyalo.sonata.profiles.repository.r2dbc.delegate;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.r2dbc.R2dbcUserProfileRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * Interface that used as delegate by {@link R2dbcUserProfileRepository} to perform all the job
 *
 * @see R2dbcUserProfileRepository
 */
public interface R2dbcProfileRepositoryDelegate extends R2dbcRepository<UserProfileEntity, Long> {

    /**
     * Search for the user in repository by user's public ID(the ID that used by external APIs)
     * @param publicId public ID of the user, not null
     * @return a {@link Mono} emitting {@link UserProfileEntity} if was found, or empty {@link Mono} if the entity with the given public ID does not exist
     */
    Mono<UserProfileEntity> findByUserInfoPublicId(@NotNull String publicId);

    /**
     * Finds a {@code UserProfileEntity} by its public ID or email.
     *
     * @param publicId the public ID of the user profile, must not be  ull
     * @param email    the email of the user profile, must not be null
     * @return a {@code Mono} emitting the found {@code UserProfileEntity}, or an empty {@code Mono} if no user profile is found
     */
    @NotNull
    Mono<UserProfileEntity> findByUserInfoPublicIdOrEmail(@NotNull String publicId, @NotNull String email);

    @NotNull
    Mono<UserProfileEntity> findByUserId(@NotNull Long userId);
}
