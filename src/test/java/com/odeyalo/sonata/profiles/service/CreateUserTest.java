package com.odeyalo.sonata.profiles.service;


import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.CreateUserInfoFaker;

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
}
