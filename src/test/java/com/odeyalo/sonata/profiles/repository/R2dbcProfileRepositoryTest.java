package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;
import testing.faker.UserProfileEntityFaker;

import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
@ActiveProfiles("test")
class R2dbcProfileRepositoryTest {

    @Autowired
    R2dbcProfileRepository testable;

    @AfterEach
    void tearDown() {
        testable.deleteAll().block();
    }

    @Test
    void shouldReturnIdOfTheUser() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withPublicId("miku")
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getPublicId()).isEqualTo("miku"))
                .verifyComplete();

    }
}
