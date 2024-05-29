package com.odeyalo.sonata.profiles.repository.memory;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.UserProfileEntityFaker;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryUserProfileRepositoryTest {

    @Test
    void shouldSaveProfile() {
        final var profile = UserProfileEntityFaker.create()
                .withPublicId("miku")
                .get();

        final InMemoryUserProfileRepository testable = new InMemoryUserProfileRepository();

        testable.save(profile)
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getId()).isNotNull())
                .verifyComplete();
    }
}