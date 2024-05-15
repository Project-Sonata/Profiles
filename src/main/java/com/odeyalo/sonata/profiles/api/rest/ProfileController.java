package com.odeyalo.sonata.profiles.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public final class ProfileController {


    @GetMapping("/{user_id}")
    public Mono<Void> fetchUserProfileById(@PathVariable("user_id") final String userId) {
        return Mono.empty();
    }

}
