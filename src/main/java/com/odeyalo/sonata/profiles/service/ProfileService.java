package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.entity.UserEntity;
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
}
