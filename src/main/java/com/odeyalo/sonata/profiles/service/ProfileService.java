package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.exception.UserAlreadyExistException;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.repository.UserRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public final class ProfileService {
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    public ProfileService(final UserRepository userRepository,
                          final UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
        this.userRepository = userRepository;
    }

    @NotNull
    public Mono<UserProfile> getProfileForUser(final String userId) {
        return userRepository.findByPublicId(userId)
                .map(UserEntity::getProfile)
                .map(userProfileMapper::toUserProfile);
    }

    @NotNull
    public Mono<UserProfile> createUser(final CreateUserInfo userInfo) {
        final var user = toUserEntity(userInfo);

        Mono<UserProfile> saveUser = userRepository.save(user)
                .map(UserEntity::getProfile)
                .map(userProfileMapper::toUserProfile);

        return userRepository.findByPublicIdOrEmail(userInfo.getId().value(), userInfo.getEmail().value())
                .flatMap(existingUser -> Mono.<UserProfile> error(UserAlreadyExistException.withCustomMessage("A user with a given ID already exist")))
                .switchIfEmpty(saveUser);
    }

    @NotNull
    private static UserEntity toUserEntity(final CreateUserInfo userInfo) {
        // maybe move this to mapstruct converter but i am not sure about it
        final UserEntity userEntity = UserEntity.builder()
                .publicId(userInfo.getId().value())
                .email(userInfo.getEmail().value())
                .contextUri("sonata:user:" + userInfo.getId().value())
                .build();

        UserProfileEntity userProfile = UserProfileEntity.builder()
                .userInfo(userEntity)
                .country(userInfo.getCountryCode())
                .birthdate(userInfo.getBirthdate().value())
                .displayName(userInfo.getUsername().value())
                .gender(userInfo.getGender())
                .build();

        return userEntity.withProfile(userProfile);
    }
}
