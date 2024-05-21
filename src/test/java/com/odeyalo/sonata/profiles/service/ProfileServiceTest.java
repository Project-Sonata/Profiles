package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.model.UserProfile;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.UserProfileFaker;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileServiceTest {

    @Test
    void shouldReturnProfileOfTheUser() {
        final var userProfile = UserProfileFaker.create()
                .withPublicId("miku")
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(userProfile)
                .build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void shouldReturnProfileDisplayName() {
        final var userProfile = UserProfileFaker.create()
                .withPublicId("miku")
                .withDisplayName("NakanoMiku")
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(userProfile)
                .build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getDisplayName()).isEqualTo("NakanoMiku"))
                .verifyComplete();
    }

    @Test
    void shouldReturnCountryOfTheUser() {
        final var userProfile = UserProfileFaker.create()
                .withPublicId("miku")
                .withCountry("JP")
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(userProfile)
                .build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getCountry()).isEqualTo("JP"))
                .verifyComplete();
    }

    static final class TestableBuilder {

        public static TestableBuilder instance() {
            return new TestableBuilder();
        }

        public TestableBuilder withProfiles(final UserProfile... profiles) {
            return this;
        }

        public ProfileService build() {
            return new ProfileService();
        }
    }
}
