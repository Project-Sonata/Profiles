package testing;

import org.springframework.context.annotation.Bean;
import org.springframework.test.web.reactive.server.WebTestClient;

public final class ProfileHttpOperationsConfiguration {

    @Bean
    public ProfileHttpOperations profileHttpOperations(final WebTestClient webTestClient) {
        return new WebTestClientProfileHttpOperations(webTestClient);
    }
}
