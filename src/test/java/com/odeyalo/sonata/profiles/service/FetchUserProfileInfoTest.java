package com.odeyalo.sonata.profiles.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.UserProfileFaker;

import java.time.LocalDate;
import java.time.Month;

import static com.odeyalo.sonata.profiles.model.Gender.FEMALE;
import static org.assertj.core.api.Assertions.assertThat;

class FetchUserProfileInfoTest extends UserProfileServiceTest {

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

    @Test
    void shouldReturnEmailOfTheUser() {
        final var userProfile = UserProfileFaker.create()
                .withPublicId("miku")
                .withEmail("mikunakano@gmail.com")
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(userProfile)
                .build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getEmail()).isEqualTo("mikunakano@gmail.com"))
                .verifyComplete();
    }

    @Test
    void shouldReturnBirthdateOfTheUser() {
        final var userProfile = UserProfileFaker.create()
                .withPublicId("miku")
                .withBirthdate(LocalDate.of(2004, Month.MAY, 17))
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(userProfile)
                .build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getBirthdate()).isEqualTo("2004-05-17"))
                .verifyComplete();
    }

    @Test
    void shouldReturnContextUriOfTheUser() {
        final var userProfile = UserProfileFaker.create()
                .withPublicId("miku")
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(userProfile)
                .build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getContextUri()).isEqualTo("sonata:user:miku"))
                .verifyComplete();
    }

    @Test
    void shouldReturnGenderOfTheUser() {
        final var userProfile = UserProfileFaker.create()
                .withPublicId("miku")
                .withGender(FEMALE)
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(userProfile)
                .build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getGender()).isEqualTo(FEMALE))
                .verifyComplete();
    }

    @Test
    void shouldReturnNothingIfProfileDoesNoeExist() {
        final ProfileService testable = TestableBuilder.instance().build();

        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .verifyComplete();
    }
}
