package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.service.CreateUserInfo;
import com.odeyalo.sonata.profiles.service.ProfileService;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

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

    @PostMapping
    public Mono<ResponseEntity<Void>> createUser(CreateUserInfo createUserInfo) {

        return profileService.createUser(createUserInfo)
                .map(
                        it -> ResponseEntity.created(URI.create("/users/" + it.getId())).build()
                );
    }
}
