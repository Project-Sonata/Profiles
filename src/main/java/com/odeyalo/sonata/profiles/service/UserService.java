package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.entity.factory.UserEntityFactory;
import com.odeyalo.sonata.profiles.exception.UserAlreadyExistException;
import com.odeyalo.sonata.profiles.model.User;
import com.odeyalo.sonata.profiles.model.core.UserId;
import com.odeyalo.sonata.profiles.repository.UserRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public final class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserEntityFactory userEntityFactory;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(final UserRepository userRepository,
                       final UserMapper userMapper,
                       final UserEntityFactory userEntityFactory) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userEntityFactory = userEntityFactory;
    }

    @NotNull
    public Mono<User> findUser(@NotNull final UserId sonataUserId) {
        return userRepository.findByPublicId(sonataUserId.value())
                .map(userMapper::toUser);
    }

    @NotNull
    public Mono<User> createUser(@NotNull final CreateUserInfo userInfo) {
        final var user = userEntityFactory.toUserEntity(userInfo);

        final Mono<User> saveUser = userRepository.save(user)
                .map(userMapper::toUser);

        return userRepository.findByPublicIdOrEmail(userInfo.getId().value(), userInfo.getEmail().value())
                .flatMap(existingUser -> Mono.<User>error(UserAlreadyExistException.withCustomMessage("A user with a given ID already exist")))
                .switchIfEmpty(saveUser)
                .doOnNext(it -> logger.debug("Saved the user with email: {}", userInfo.getEmail()));
    }
}


