package com.odeyalo.sonata.profiles.repository.memory;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.UserProfileEntityFaker;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryUserProfileRepositoryTest {

    @Test
    void shouldFindProfileByPublicId() {
        final var profile = UserProfileEntityFaker.create()
                .withPublicId("miku")
                .get();

        final InMemoryUserProfileRepository testable = InMemoryUserProfileRepository.withPredefinedEntities(profile);

        testable.findByPublicId("miku")
                .as(StepVerifier::create)
                .expectNext(profile)
                .verifyComplete();
    }
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

    @Test
    void shouldFindUserByItsId() {
        final var profile = UserProfileEntityFaker.create()
                .withPublicId("miku")
                .get();

        final InMemoryUserProfileRepository testable = InMemoryUserProfileRepository.withPredefinedEntities(profile);

        testable.findByPublicIdOrEmail("miku", "notExist@gmail.com")
                .as(StepVerifier::create)
                .expectNext(profile)
                .verifyComplete();
    }

    @Test
    void shouldFindUserByItsEmail() {
        final var profile = UserProfileEntityFaker.create()
                .withEmail("miku@gmail.com")
                .get();

        final InMemoryUserProfileRepository testable = InMemoryUserProfileRepository.withPredefinedEntities(profile);

        testable.findByPublicIdOrEmail("notExist", "miku@gmail.com")
                .as(StepVerifier::create)
                .expectNext(profile)
                .verifyComplete();
    }

    @Test
    void shouldReturnNothingIfNotExistByIdAndEmail() {
        final var profile = UserProfileEntityFaker.create().get();

        final InMemoryUserProfileRepository testable = InMemoryUserProfileRepository.withPredefinedEntities(profile);

        testable.findByPublicIdOrEmail("notExist", "notExist@gmail.com")
                .as(StepVerifier::create)
                .verifyComplete();
    }
}