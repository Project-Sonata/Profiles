package com.odeyalo.sonata.profiles.api.rest;


import com.odeyalo.sonata.profiles.service.CreateUserInfo;
import com.odeyalo.sonata.profiles.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/users")
public final class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> createUser(CreateUserInfo createUserInfo) {

        return userService.createUser(createUserInfo)
                .map(
                        it -> ResponseEntity.created(URI.create("/users/" + it.getProfile().getId().value())).build()
                );
    }
}
