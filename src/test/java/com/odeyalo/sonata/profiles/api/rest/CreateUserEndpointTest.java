package com.odeyalo.sonata.profiles.api.rest;


import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class CreateUserEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldReturn201CreatedOnSuccess() {
        final WebTestClient.ResponseSpec responseSpec = createUserRequest();

        responseSpec.expectStatus().isCreated();
    }

    @NotNull
    private WebTestClient.ResponseSpec createUserRequest() {
        return webTestClient.post()
                .uri("/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange();
    }
}
