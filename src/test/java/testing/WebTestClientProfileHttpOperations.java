package testing;

import com.odeyalo.sonata.profiles.api.dto.CreateUserInfoDto;
import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

/**
 * {@link ProfileHttpOperations} implementation that uses {@link WebTestClient}
 */
public final class WebTestClientProfileHttpOperations implements ProfileHttpOperations {
    private final WebTestClient webTestClient;

    public WebTestClientProfileHttpOperations(final WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Override
    public URI createUser(final CreateUserInfoDto body) {
        return webTestClient.post()
                .uri("/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body).exchange()
                .expectStatus().isCreated()
                .returnResult(Void.class)
                .getResponseHeaders()
                .getLocation();
    }

    @Override
    public UserProfileDto getUserProfile(final String userId) {
        return webTestClient.get()
                .uri("/users/" + userId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserProfileDto.class)
                .returnResult()
                .getResponseBody();
    }
}
