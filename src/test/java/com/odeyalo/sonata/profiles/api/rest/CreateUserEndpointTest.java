package com.odeyalo.sonata.profiles.api.rest;


import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class CreateUserEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldReturn201CreatedOnSuccess() {
        final var body = CreateUserInfoDto.builder()
                .username("odeyalo")
                .birthdate(LocalDate.now())
                .email("odeyalo@gmail.com")
                .build();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isCreated();
    }

    @NotNull
    private WebTestClient.ResponseSpec sendCreateUserRequest(@NotNull CreateUserInfoDto body) {
        return webTestClient.post()
                .uri("/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .exchange();
    }
}
