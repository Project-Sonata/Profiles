package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.config.mapper.Converters;
import com.odeyalo.sonata.profiles.entity.BasicUserInfo;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.repository.memory.InMemoryUserProfileRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base class for {@link ProfileService} functionality tests
 */
public abstract class UserProfileServiceTest {

    protected static final class TestableBuilder {
        private final List<UserProfileEntity> predefinedEntities = new ArrayList<>();

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
            final InMemoryUserProfileRepository repository = InMemoryUserProfileRepository.withPredefinedEntities(predefinedEntities);
            final UserProfileMapper userProfileMapper = new Converters().userProfileMapper();

            return new ProfileService(repository, userProfileMapper);
        }

        // Don't like stuff like this in tests, change it by using Adapter?
        private static UserProfileEntity toUserProfileEntity(final UserProfile profile) {
            return UserProfileEntity.builder()
                    .userInfo(
                            BasicUserInfo.builder()
                                    .publicId(profile.getId())
                                    .contextUri(profile.getContextUri())
                                    .build()
                    )
                    .displayName(profile.getDisplayName())
                    .email(profile.getEmail())
                    .birthdate(profile.getBirthdate())
                    .country(profile.getCountry())
                    .gender(profile.getGender())
                    .build();
        }
    }
}
