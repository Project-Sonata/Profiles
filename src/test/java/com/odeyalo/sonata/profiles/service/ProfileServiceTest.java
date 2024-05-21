package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.model.UserProfile;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.UserProfileFaker;

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
