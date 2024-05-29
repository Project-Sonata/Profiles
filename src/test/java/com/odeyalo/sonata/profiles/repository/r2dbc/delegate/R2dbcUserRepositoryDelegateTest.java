package com.odeyalo.sonata.profiles.repository.r2dbc.delegate;

import com.odeyalo.sonata.profiles.config.persistance.r2dbc.R2dbcConfiguration;
import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.repository.r2dbc.support.AssociateUserProfileAfterConvertCallback;
import com.odeyalo.sonata.profiles.repository.r2dbc.support.SaveUserProfileAfterSaveCallback;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;
import testing.faker.UserProfileEntityFaker;

import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
@ActiveProfiles("test")
@Import({SaveUserProfileAfterSaveCallback.class, R2dbcConfiguration.class, AssociateUserProfileAfterConvertCallback.class})
class R2dbcUserRepositoryDelegateTest {

    @Autowired
    R2dbcUserRepositoryDelegate testable;

    @Autowired
    R2dbcProfileRepositoryDelegate userProfileRepository;

    @AfterEach
    void tearDown() {
        userProfileRepository.deleteAll()
                .then(testable.deleteAll()).block();
    }

    @Test
    void shouldFindUserByPublicId() {
        final UserEntity user = UserEntity.builder()
                .publicId("miku")
                .contextUri("sonata:user:miku")
                .email("miku@gmail.com")
                .build();

        final UserProfileEntity userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withUserInfo(user)
                .get();

        final UserEntity savingTarget = user.withProfile(userProfile);

        final UserEntity savedUser = testable.save(savingTarget).block();

        testable.findByPublicId("miku")
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it).usingRecursiveComparison()
                        .ignoringFields("profile").isEqualTo(savedUser))
                .verifyComplete();
    }

    @Test
    void shouldSaveProfileForUserOnMissing() {
        final UserEntity user = UserEntity.builder()
                .publicId("miku")
                .contextUri("sonata:user:miku")
                .email("miku@gmail.com")
                .build();

        final UserProfileEntity userProfile = UserProfileEntityFaker.create()
                .eraseId()
                .withUserInfo(user)
                .get();

        final UserEntity savingTarget = user.withProfile(userProfile);

        final UserEntity savedUser = testable.save(savingTarget).block();

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getProfile().getId()).isNotNull();

        userProfileRepository.findById(savedUser.getProfile().getId())
                .as(StepVerifier::create)
                .assertNext(it -> assertThat(it.getUserId()).isEqualTo(savedUser.getId()))
                .verifyComplete();
    }
}