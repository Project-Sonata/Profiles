package com.odeyalo.sonata.profiles.entity.factory;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.service.CreateUserInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Create an instance of {@link UserEntity} from {@link CreateUserInfo}
 */
@Component
public final class UserEntityFactory {

    @NotNull
    public UserEntity toUserEntity(@NotNull final CreateUserInfo userInfo) {
        final UserEntity userEntity = UserEntity.builder()
                .publicId(userInfo.getId().value())
                .email(userInfo.getEmail().value())
                .contextUri("sonata:user:" + userInfo.getId().value())
                .build();

        final UserProfileEntity userProfile = UserProfileEntity.builder()
                .userInfo(userEntity)
                .country(userInfo.getCountryCode())
                .birthdate(userInfo.getBirthdate().value())
                .displayName(userInfo.getUsername().value())
                .gender(userInfo.getGender())
                .build();

        return userEntity.withProfile(userProfile);
    }

}
