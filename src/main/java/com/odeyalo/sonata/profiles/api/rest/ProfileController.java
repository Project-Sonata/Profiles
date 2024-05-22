package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.service.ProfileService;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileDtoMapper;
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
    private final UserProfileDtoMapper userProfileDtoMapper;

    public ProfileController(final ProfileService profileService,
                             final UserProfileDtoMapper userProfileDtoMapper) {
        this.profileService = profileService;
        this.userProfileDtoMapper = userProfileDtoMapper;
    }

    @GetMapping("/{user_id}")
    public Mono<ResponseEntity<UserProfileDto>> fetchUserProfileById(@PathVariable("user_id") final String userId) {

        return profileService.getProfileForUser(userId)
                .map(userProfileDtoMapper::toUserProfileDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
