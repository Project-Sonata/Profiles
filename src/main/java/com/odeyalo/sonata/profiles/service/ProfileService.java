package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.model.Gender;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.repository.UserProfileRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public final class ProfileService {
    private final UserProfileRepository profileRepository;
    private final UserProfileMapper userProfileMapper;

    public ProfileService(final UserProfileRepository profileRepository, final UserProfileMapper userProfileMapper) {
        this.profileRepository = profileRepository;
        this.userProfileMapper = userProfileMapper;
    }

    @NotNull
    public Mono<UserProfile> getProfileForUser(final String userId) {
        return profileRepository.findByPublicId(userId)
                .map(userProfileMapper::toUserProfile);
    }

    @NotNull
    public Mono<UserProfile> createUser(final CreateUserInfo userInfo) {
        return Mono.just(
                UserProfile.builder()
                        .id(userInfo.getId().value())
                        .email("mock")
                        .contextUri("mock")
                        .country("mock")
                        .birthdate(LocalDate.of(1000, 2, 3))
                        .displayName("mock")
                        .gender(Gender.NONE)
                        .build());
    }
}
