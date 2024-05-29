package com.odeyalo.sonata.profiles.repository;

import com.odeyalo.sonata.profiles.config.persistance.r2dbc.R2dbcConfiguration;
import com.odeyalo.sonata.profiles.entity.BasicUserInfo;
import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.Gender;
import com.odeyalo.sonata.profiles.repository.r2dbc.R2dbcUserProfileRepository;
import com.odeyalo.sonata.profiles.repository.r2dbc.R2dbcUserRepository;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcProfileRepositoryDelegate;
import com.odeyalo.sonata.profiles.repository.r2dbc.delegate.R2dbcUserRepositoryDelegate;
import com.odeyalo.sonata.profiles.repository.r2dbc.support.AssociateUserProfileAfterConvertCallback;
import com.odeyalo.sonata.profiles.repository.r2dbc.support.SaveUserProfileAfterSaveCallback;
import com.odeyalo.sonata.profiles.repository.r2dbc.support.UserInfoEnhancerAfterConvertCallback;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
@Import({R2dbcConfiguration.class, AssociateUserProfileAfterConvertCallback.class, SaveUserProfileAfterSaveCallback.class, UserInfoEnhancerAfterConvertCallback.class})
class R2dbcUserProfileRepositoryTest {

    @Autowired
    R2dbcUserProfileRepository testable;

    @Autowired
    UserRepository userRepository;

    BasicUserInfo user;

    @BeforeEach
    void setUp() {
        final UserEntity toSave = UserEntity.builder()
                .publicId("miku")
                .contextUri("sonata:user:miku")
                .email("miku@gmail.com")
                .build();

        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withPublicId("miku")
                .withDisplayName("miku_nakano")
                .withEmail("miku@gmail.com")
                .withCountry("JP")
                .withBirthdate(LocalDate.of(2003, MAY, 2))
                .withContextUri("sonata:user:miku")
                .withUserInfo(toSave)
                .get();

        user = userRepository.save(toSave.withProfile(userProfile)).block();
    }

    @TestConfiguration
    static class Config {

        @Bean
        public R2dbcUserProfileRepository r2dbcUserProfileRepository(final R2dbcProfileRepositoryDelegate delegate) {
            return new R2dbcUserProfileRepository(delegate);
        }

        @Bean
        public UserRepository userRepository(final R2dbcUserRepositoryDelegate delegate) {
            return new R2dbcUserRepository(delegate);
        }
    }

    @AfterEach
    void tearDown() {
        testable.deleteAll().block();
        userRepository.deleteAll().block();
    }

    @Test
    void shouldReturnPublicIdOfTheUser() {
        testable.findByUserId(user.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getUserInfo().getPublicId()).isEqualTo("miku"))
                .verifyComplete();
    }

    @Test
    void shouldReturnDisplayNameOfTheUser() {
        testable.findByUserId(user.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getDisplayName()).isEqualTo("miku_nakano"))
                .verifyComplete();
    }

    @Test
    void shouldReturnEmailOfTheUser() {
        testable.findByUserId(user.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getUserInfo().getEmail()).isEqualTo("miku@gmail.com"))
                .verifyComplete();
    }

    @Test
    void shouldReturnCountryOfTheUser() {
        testable.findByUserId(user.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getCountry()).isEqualTo("JP"))
                .verifyComplete();
    }

    @Test
    void shouldReturnBirthdateOfTheUser() {
        testable.findByUserId(user.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getBirthdate()).isEqualTo("2003-05-02"))
                .verifyComplete();
    }

    @ParameterizedTest
    @EnumSource(value = Gender.class)
    @Disabled
    void shouldReturnGenderOfTheUser(final Gender gender) {
        final UserEntity user = UserEntity.builder()
                .publicId("miku")
                .contextUri("sonata:user:miku")
                .email("miku@gmail.com")
                .build();

        final var userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withGender(gender)
                .withUserInfo(user)
                .get();

        final UserProfileEntity saved = testable.save(userProfile).block();

        //noinspection DataFlowIssue there is no way that after save ID will be null
        testable.findById(saved.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getGender()).isEqualTo(gender))
                .verifyComplete();
    }

    @Test
    void shouldReturnContextUriOfTheProfile() {
        testable.findByUserId(user.getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getUserInfo().getContextUri()).isEqualTo("sonata:user:miku"))
                .verifyComplete();
    }

}
