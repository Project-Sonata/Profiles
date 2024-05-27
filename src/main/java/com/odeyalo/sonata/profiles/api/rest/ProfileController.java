package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.UserProfileRepository;
import com.odeyalo.sonata.profiles.service.ProfileService;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileDtoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/users")
public final class ProfileController {
    private final ProfileService profileService;
    private final UserProfileDtoMapper userProfileDtoMapper;

    @Autowired
    UserProfileRepository profileRepository;

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
    public Mono<ResponseEntity<Void>> createUser(@RequestBody @Valid CreateUserInfoDto body) {
        final var profile = UserProfileEntity.builder()
                .publicId(body.getId())
                .email(body.getEmail())
                .country(body.getCountryCode())
                .displayName(body.getUsername())
                .gender(body.getGender())
                .birthdate(body.getBirthdate())
                .contextUri("sonata:user:" + body.getId())
                .build();

        return profileRepository.save(profile)
                .map(it -> ResponseEntity.created(URI.create(
                                "/users/" + body.getId()
                        )).build()
                );
    }
}
