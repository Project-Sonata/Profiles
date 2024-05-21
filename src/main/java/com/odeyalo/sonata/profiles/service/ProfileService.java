package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.repository.UserProfileRepository;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public final class ProfileService {
    private final UserProfileRepository profileRepository;

    public ProfileService(final UserProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @NotNull
    public Mono<UserProfile> getProfileForUser(final String userId) {
        return profileRepository.findByPublicId(userId)
                .map(ProfileService::toUserProfile);
    }

    private static UserProfile toUserProfile(final UserProfileEntity it) {
        return UserProfile.builder()
                .id(it.getPublicId())
                .displayName(it.getDisplayName())
                .country(it.getCountry())
                .email(it.getEmail())
                .birthdate(it.getBirthdate())
                .contextUri(it.getContextUri())
                .gender(it.getGender())
                .build();
    }
}
