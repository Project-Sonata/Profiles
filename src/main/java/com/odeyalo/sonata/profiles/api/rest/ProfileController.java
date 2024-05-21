package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public final class ProfileController {
    private final ProfileService profileService;

    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{user_id}")
    public Mono<ResponseEntity<UserProfileDto>> fetchUserProfileById(@PathVariable("user_id") final String userId) {

        return profileService.getProfileForUser(userId)
                .map(it -> UserProfileDto.builder()
                        .id(userId)
                        .contextUri(it.getContextUri())
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
