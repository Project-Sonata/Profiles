package com.odeyalo.sonata.profiles.service;


import com.odeyalo.sonata.profiles.exception.UserAlreadyExistException;
import com.odeyalo.sonata.profiles.model.Gender;
import com.odeyalo.sonata.profiles.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import reactor.test.StepVerifier;
import testing.faker.CreateUserInfoFaker;
import testing.faker.UserProfileFaker;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class CreateUserTest extends UserProfileServiceTest {

    @Test
    void shouldReturnUserWithTheSameId() {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withId("miku")
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                // then
                .assertNext(it -> assertThat(it.getId()).isEqualTo("miku"))
                .verifyComplete();
    }

    @Test
    void shouldReturnUserWithTheSameEmail() {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withEmail("miku.nakano@gmail.com")
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                // then
                .assertNext(it -> assertThat(it.getEmail()).isEqualTo("miku.nakano@gmail.com"))
                .verifyComplete();
    }

    @Test
    void shouldReturnUserWithTheSameCountry() {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withCountryCode("JP")
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                // then
                .assertNext(it -> assertThat(it.getCountry()).isEqualTo("JP"))
                .verifyComplete();
    }

    @Test
    void shouldReturnUserWithTheSameBirthdate() {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withBirthdate(LocalDate.of(2000, Month.JANUARY, 25))
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                // then
                .assertNext(it -> assertThat(it.getBirthdate()).isEqualTo("2000-01-25"))
                .verifyComplete();
    }

    @ParameterizedTest
    @EnumSource(value = Gender.class)
    void shouldReturnUserWithTheSameGender(final Gender gender) {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withGender(gender)
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                // then
                .assertNext(it -> assertThat(it.getGender()).isEqualTo(gender))
                .verifyComplete();
    }

    @Test
    void shouldReturnUserWithContextUri() {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withId("miku")
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                // then
                .assertNext(it -> assertThat(it.getContextUri()).isEqualTo("sonata:user:miku"))
                .verifyComplete();
    }

    @Test
    void shouldUseUsernameAsDisplayName() {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withUsername("Nakano.Miku")
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                // then
                .assertNext(it -> assertThat(it.getDisplayName()).isEqualTo("Nakano.Miku"))
                .verifyComplete();
    }

    @Test
    void userShouldBeFetchedAfterSaving() {
        // given
        final ProfileService testable = TestableBuilder.instance().build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withId("miku")
                .get();
        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        // then
        testable.getProfileForUser("miku")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void shouldReturnErrorIfUserWithIdAlreadyExist() {
        // given
        final UserProfile profile1 = UserProfileFaker.create()
                .withPublicId("miku")
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(profile1)
                .build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withId("miku")
                .get();

        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                .expectError(UserAlreadyExistException.class)
                .verify();
    }
    @Test
    void shouldReturnErrorIfUserWithEmailAlreadyExist() {
        // given
        final UserProfile profile1 = UserProfileFaker.create()
                .withEmail("miku.nakano@gmail.com")
                .get();

        final ProfileService testable = TestableBuilder.instance()
                .withProfiles(profile1)
                .build();

        final CreateUserInfo payload = CreateUserInfoFaker.create()
                .withEmail("miku.nakano@gmail.com")
                .get();

        // when
        testable.createUser(payload)
                .as(StepVerifier::create)
                .expectError(UserAlreadyExistException.class)
                .verify();
    }
}
