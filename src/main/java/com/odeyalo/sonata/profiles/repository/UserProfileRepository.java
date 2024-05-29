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
     * Finds a {@code UserProfileEntity} by user's ID.
     *
     * @param userId - a user ID assocciated with the profile
     * @return a {@code Mono} emitting the found {@code UserProfileEntity}, or an empty {@code Mono} if no user profile is found
     */
    @NotNull
    Mono<UserProfileEntity> findByUserId(@NotNull Long userId);
}
