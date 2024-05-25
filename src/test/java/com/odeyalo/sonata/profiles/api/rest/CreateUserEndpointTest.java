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
import testing.faker.CreateUserInfoDtoFaker;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class CreateUserEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldReturn201CreatedOnSuccess() {
        final var body = CreateUserInfoDtoFaker.create().get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isCreated();
    }

    @Test
    void shouldReturnLocationUriWithCreatedResource() {
        final var body = CreateUserInfoDtoFaker.create()
                .withId("miku")
                .get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);
        // ID should be the same as we provided
        responseSpec.expectHeader().location("/users/miku");
    }

    @Test
    void shouldReturnBadRequestIfEmailIsNotValidFormat() {
        final var body = CreateUserInfoDtoFaker.create()
                .withEmail("invalid_format")
                .get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnBadRequestIfBirthdateIsLessThan14YearsOld() {
        final var body = CreateUserInfoDtoFaker.create()
                .withBirthdate(LocalDate.now().minusYears(12))
                .get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnBadRequestIfBirthdateIsInTheFuture() {
        final var body = CreateUserInfoDtoFaker.create()
                .withBirthdate(LocalDate.now().plusYears(10))
                .get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isBadRequest();
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
