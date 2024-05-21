package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.model.Gender;
import com.odeyalo.sonata.profiles.model.UserProfile;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;

public final class ProfileService {

    @NotNull
    public Mono<UserProfile> getProfileForUser(final String userId) {
        return Mono.just(
                UserProfile.builder()
                        .id(userId)
                        .displayName("NakanoMiku")
                        .country("JP")
                        .email("mikunakano@gmail.com")
                        .birthdate(LocalDate.of(2004, Month.MAY, 17))
                        .contextUri("sonata:user:" + userId)
                        .gender(Gender.FEMALE)
                        .build()
        );
    }
}
