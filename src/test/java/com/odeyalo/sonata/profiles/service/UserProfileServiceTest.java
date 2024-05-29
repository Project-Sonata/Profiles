package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.config.mapper.Converters;
import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.repository.memory.InMemoryUserRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileMapper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base class for {@link ProfileService} functionality tests
 */
public abstract class UserProfileServiceTest {

    protected static final class TestableBuilder {
        private final List<UserEntity> predefinedEntities = new ArrayList<>();

        public static TestableBuilder instance() {
            return new TestableBuilder();
        }

        public TestableBuilder withProfiles(final UserProfile... profiles) {

            final var entities = Arrays.stream(profiles)
                    .map(TestableBuilder::toUserProfileEntity)
                    .toList();

            predefinedEntities.addAll(entities);

            return this;
        }

        public ProfileService build() {
            final InMemoryUserRepository repository = InMemoryUserRepository.withPredefinedEntities(predefinedEntities);
            final UserProfileMapper userProfileMapper = new Converters().userProfileMapper();

            return new ProfileService(repository, userProfileMapper);
        }

        // Don't like stuff like this in tests, change it by using Adapter?
        private static @NotNull UserEntity toUserProfileEntity(final UserProfile profile) {
            final UserEntity userEntity = UserEntity.builder()
                    .publicId(profile.getId())
                    .contextUri(profile.getContextUri())
                    .email(profile.getEmail())
                    .build();

            UserProfileEntity profileEntity = UserProfileEntity.builder()
                    .userInfo(userEntity)
                    .displayName(profile.getDisplayName())
                    .birthdate(profile.getBirthdate())
                    .country(profile.getCountry())
                    .gender(profile.getGender())
                    .build();

            return userEntity.withProfile(profileEntity);
        }
    }
}
