package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.exception.UserAlreadyExistException;
import com.odeyalo.sonata.profiles.model.User;
import com.odeyalo.sonata.profiles.repository.UserRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public final class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(final UserRepository userRepository,
                       final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @NotNull
    public Mono<User> findUser(@NotNull final String sonataUserId) {
        return userRepository.findByPublicId(sonataUserId)
                .map(userMapper::toUser);
    }

    @NotNull
    public Mono<User> createUser(final CreateUserInfo userInfo) {
        final var user = toUserEntity(userInfo);

        final Mono<User> saveUser = userRepository.save(user)
                .map(userMapper::toUser);

        return userRepository.findByPublicIdOrEmail(userInfo.getId().value(), userInfo.getEmail().value())
                .flatMap(existingUser -> Mono.<User>error(UserAlreadyExistException.withCustomMessage("A user with a given ID already exist")))
                .switchIfEmpty(saveUser)
                .log();
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
