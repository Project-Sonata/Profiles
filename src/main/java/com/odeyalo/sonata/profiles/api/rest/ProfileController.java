package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.repository.R2dbcProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public final class ProfileController {

    @Autowired
    R2dbcProfileRepository r2dbcProfileRepository;

    @GetMapping("/{user_id}")
    public Mono<ResponseEntity<UserProfileDto>> fetchUserProfileById(@PathVariable("user_id") final String userId) {

        return r2dbcProfileRepository.findByPublicId(userId)
                .map(it -> UserProfileDto.builder()
                        .id(userId)
                        .contextUri("sonata:user:" + userId)
                        .displayName(it.getDisplayName())
                        .email(it.getEmail())
                        .birthdate(it.getBirthdate())
                        .gender(it.getGender())
                        .countryCode(it.getCountry())
                        .build())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
