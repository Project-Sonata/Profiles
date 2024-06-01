package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.config.mapper.Converters;
import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.UserProfile;
import com.odeyalo.sonata.profiles.repository.memory.InMemoryUserRepository;
import com.odeyalo.sonata.profiles.support.mapper.UserMapper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class UserServiceTest {

    protected static final class TestableBuilder {
        private final List<UserEntity> predefinedEntities = new ArrayList<>();

        public static UserServiceTest.TestableBuilder instance() {
            return new UserServiceTest.TestableBuilder();
        }

        public UserServiceTest.TestableBuilder withProfiles(final UserProfile... profiles) {

            final var entities = Arrays.stream(profiles)
                    .map(UserServiceTest.TestableBuilder::toUserProfileEntity)
                    .toList();

            predefinedEntities.addAll(entities);

            return this;
        }

        public UserService build() {
            final InMemoryUserRepository repository = InMemoryUserRepository.withPredefinedEntities(predefinedEntities);
            final UserMapper userProfileMapper = new Converters().userMapper();

            return new UserService(repository, userProfileMapper);
        }

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
