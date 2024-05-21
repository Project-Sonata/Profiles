package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.model.UserProfile;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public final class ProfileService {

    @NotNull
    public Mono<UserProfile> getProfileForUser(final String userId) {
        return Mono.just(
                UserProfile.builder()
                        .id(userId)
                        .build()
        );
    }
}
