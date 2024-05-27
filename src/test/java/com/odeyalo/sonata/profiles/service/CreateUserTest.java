package com.odeyalo.sonata.profiles.service;


import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.CreateUserInfoFaker;

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
                .assertNext(it -> assertThat(it.getId()).isNotNull())
                .verifyComplete();
    }
}
