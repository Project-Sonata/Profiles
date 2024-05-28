package com.odeyalo.sonata.profiles.api.rest;


import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.repository.UserProfileRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.AutoConfigureProfileHttpOperations;
import testing.ProfileHttpOperations;
import testing.faker.CreateUserInfoDtoFaker;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
@AutoConfigureProfileHttpOperations
@ActiveProfiles("test")
class CreateUserEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    ProfileHttpOperations profileHttpOperations;


    @Autowired
    UserProfileRepository userProfileRepository;

    @AfterEach
    void tearDown() {
        userProfileRepository.deleteAll().block();
    }

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
    void createdUserShouldBeSaved() {
        final var body = CreateUserInfoDtoFaker.create()
                .withId("miku")
                .get();

        final WebTestClient.ResponseSpec ignored = sendCreateUserRequest(body);

        final UserProfileDto userProfile = profileHttpOperations.getUserProfile("miku");

        assertThat(userProfile).isNotNull();
    }

    @Test
    void shouldReturn400BadRequestIfUserWithIdAlreadyExist() {
        final var body = CreateUserInfoDtoFaker.create()
                .withId("miku")
                .get();

        final WebTestClient.ResponseSpec ignored = sendCreateUserRequest(body);

        final WebTestClient.ResponseSpec secondRequest = sendCreateUserRequest(body);

        // We expect that the second request with same payload will produce error
        secondRequest.expectStatus().isBadRequest();
    }

    @Test
    void shouldReturn400BadRequestIfUserWithEmailAlreadyExist() {
        final var body = CreateUserInfoDtoFaker.create()
                .withEmail("miku.nakano@gmail.com")
                .get();

        final WebTestClient.ResponseSpec firstRequest = sendCreateUserRequest(body);

        firstRequest.expectStatus().isCreated();

        final WebTestClient.ResponseSpec secondRequest = sendCreateUserRequest(body);

        // We expect that the second request with same payload will produce error
        secondRequest.expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnBadRequestIfIdIsNull() {
        final var body = CreateUserInfoDtoFaker.create()
                .withId(null)
                .get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnBadRequestIfUsernameIsNull() {
        final var body = CreateUserInfoDtoFaker.create()
                .withUsername(null)
                .get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isBadRequest();
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

    @Test
    void shouldReturnBadRequestIfGenderNotIncluded() {
        final var body = CreateUserInfoDtoFaker.create()
                .withGender(null)
                .get();

        final WebTestClient.ResponseSpec responseSpec = sendCreateUserRequest(body);

        responseSpec.expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnBadRequestIfCountryIsNotISO3122V2() {
        final var body = CreateUserInfoDtoFaker.create()
                .withCountry("JPS")
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
