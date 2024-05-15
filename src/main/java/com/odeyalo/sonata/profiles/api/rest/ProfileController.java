package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.model.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public final class ProfileController {


    @GetMapping("/{user_id}")
    public Mono<ResponseEntity<UserProfileDto>> fetchUserProfileById(@PathVariable("user_id") final String userId) {

        if ( Objects.equals(userId, "not_exist") ) {
            return Mono.just(
                    ResponseEntity.noContent().build()
            );
        }

        return Mono.just(
                ResponseEntity.ok(
                        UserProfileDto.builder()
                                .id(userId)
                                .contextUri("sonata:user:miku")
                                .displayName("odeyalo")
                                .email("odeyalo@gmail.com")
                                .birthdate(LocalDate.of(2004, Month.MAY, 22))
                                .gender(Gender.FEMALE)
                                .countryCode("JP")
                                .build()
                )
        );
    }
}
