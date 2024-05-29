package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.entity.BasicUserInfo;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.exception.UserAlreadyExistException;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.repository.UserProfileRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
        final var userProfile = toUserProfileEntity(userInfo);

        Mono<UserProfile> saveUser = profileRepository.save(userProfile)
                .map(userProfileMapper::toUserProfile);

        return profileRepository.findByPublicIdOrEmail(userInfo.getId().value(), userInfo.getEmail().value())
                .flatMap(existingUser -> Mono.<UserProfile> error(UserAlreadyExistException.withCustomMessage("A user with a given ID already exist")))
                .switchIfEmpty(saveUser);
    }

    @NotNull
    private static UserProfileEntity toUserProfileEntity(final CreateUserInfo userInfo) {
        // maybe move this to mapstruct converter but i am not sure about it
        final BasicUserInfo userEntity = BasicUserInfo.builder()
                .publicId(userInfo.getId().value())
                .contextUri("sonata:user:" + userInfo.getId().value())
                .build();

        return UserProfileEntity.builder()
                .userInfo(userEntity)
                .email(userInfo.getEmail().value())
                .country(userInfo.getCountryCode())
                .birthdate(userInfo.getBirthdate().value())
                .displayName(userInfo.getUsername().value())
                .gender(userInfo.getGender())
                .build();
    }
}
