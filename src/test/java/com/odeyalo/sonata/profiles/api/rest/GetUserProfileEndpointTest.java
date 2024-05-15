package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.odeyalo.sonata.profiles.model.Gender.FEMALE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class GetUserProfileEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    final String EXISTING_USER_ID = "miku";

    @Test
    void shouldReturn200OkStatus() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        responseSpec.expectStatus().isOk();
    }

    @Test
    void shouldReturnDisplayNameOfTheUser() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        final UserProfileDto responseBody = responseSpec.expectBody(UserProfileDto.class).returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getDisplayName()).isEqualTo("odeyalo");
    }

    @Test
    void shouldReturnEmailOfTheUser() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        final UserProfileDto responseBody = responseSpec.expectBody(UserProfileDto.class).returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getEmail()).isEqualTo("odeyalo@gmail.com");
    }

    @Test
    void shouldReturnIdSameToProvided() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        final UserProfileDto responseBody = responseSpec.expectBody(UserProfileDto.class).returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isEqualTo(EXISTING_USER_ID);
    }

    @Test
    void shouldReturnContextUriOfTheUser() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        final UserProfileDto responseBody = responseSpec.expectBody(UserProfileDto.class).returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getContextUri()).isEqualTo("sonata:user:miku");
    }

    @Test
    void shouldReturnBirthdateOfTheUser() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        final UserProfileDto responseBody = responseSpec.expectBody(UserProfileDto.class).returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getBirthdate()).isEqualTo("2004-05-22");
    }

    @Test
    void shouldReturnGenderOfTheUser() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        final UserProfileDto responseBody = responseSpec.expectBody(UserProfileDto.class).returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getGender()).isEqualTo(FEMALE);
    }

    @Test
    void shouldReturnCountryCodeOfTheUser() {
        final WebTestClient.ResponseSpec responseSpec = fetchExistingUserProfile();

        final UserProfileDto responseBody = responseSpec.expectBody(UserProfileDto.class).returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getCountryCode()).isEqualTo("JP");
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class FetchNotExistingProfile {
        final String NOT_EXISTING_USER_ID = "not_exist";

        @Test
        void shouldReturnNoContent() {
            WebTestClient.ResponseSpec responseSpec = fetchNotExistingUserProfile();

            responseSpec.expectStatus().isNoContent();
        }

        private WebTestClient.ResponseSpec fetchNotExistingUserProfile() {
            return sendFetchUserProfileRequest(NOT_EXISTING_USER_ID);
        }
    }

    private WebTestClient.ResponseSpec fetchExistingUserProfile() {
        return sendFetchUserProfileRequest(EXISTING_USER_ID);
    }

    @NotNull
    private WebTestClient.ResponseSpec sendFetchUserProfileRequest(final String userId) {
        return webTestClient.get()
                .uri("/users/" + userId)
                .exchange();
    }
}
