package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.config.persistance.r2dbc.R2dbcConfiguration;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.Gender;
import com.odeyalo.sonata.profiles.repository.r2dbc.R2dbcUserProfileRepository;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcProfileRepositoryDelegate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;
import testing.faker.UserProfileEntityFaker;

import java.time.LocalDate;

import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
@ActiveProfiles("test")
@Import(R2dbcConfiguration.class)
class R2dbcUserProfileRepositoryTest {

    @Autowired
    R2dbcUserProfileRepository testable;

    @TestConfiguration
    static class Config {

        @Bean
        public R2dbcUserProfileRepository r2dbcUserProfileRepository(final R2dbcProfileRepositoryDelegate delegate) {
            return new R2dbcUserProfileRepository(delegate);
        }
    }

    @AfterEach
    void tearDown() {
        testable.deleteAll().block();
    }

    @Test
    void shouldReturnPublicIdOfTheUser() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withPublicId("miku")
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getUserInfo().getPublicId()).isEqualTo("miku"))
                .verifyComplete();
    }

    @Test
    void shouldReturnDisplayNameOfTheUser() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withDisplayName("miku_nakano")
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getDisplayName()).isEqualTo("miku_nakano"))
                .verifyComplete();
    }

    @Test
    void shouldReturnEmailOfTheUser() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withEmail("miku@gmail.com")
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getUserInfo().getEmail()).isEqualTo("miku@gmail.com"))
                .verifyComplete();
    }
    @Test
    void shouldReturnCountryOfTheUser() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withCountry("JP")
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getCountry()).isEqualTo("JP"))
                .verifyComplete();
    }

    @Test
    void shouldReturnBirthdateOfTheUser() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withBirthdate(LocalDate.of(2003, MAY, 2))
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getBirthdate()).isEqualTo("2003-05-02"))
                .verifyComplete();
    }

    @ParameterizedTest
    @EnumSource(value = Gender.class)
    void shouldReturnGenderOfTheUser(final Gender gender) {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withGender(gender)
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getGender()).isEqualTo(gender))
                .verifyComplete();
    }

    @Test
    void shouldFindProfileByPublicId() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withPublicId("miku")
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue
        testable.findByPublicId("miku")
                .as(StepVerifier::create)
                // assert that the next element is same as saved in database
                .expectNext(saved)
                .verifyComplete();
    }


    @Test
    void shouldReturnContextUriOfTheProfile() {
        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withContextUri("sonata:user:miku")
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getUserInfo().getContextUri()).isEqualTo("sonata:user:miku"))
                .verifyComplete();
    }

}
