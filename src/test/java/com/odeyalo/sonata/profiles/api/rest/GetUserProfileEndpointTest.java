package com.odeyalo.sonata.profiles.api.rest;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

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

    private WebTestClient.ResponseSpec fetchExistingUserProfile() {
        return webTestClient.get()
                .uri("/users/" + EXISTING_USER_ID)
                .exchange();
    }
}
