package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        uses = {
                UserIdConverter.class,
                EmailConverter.class,
                BirthdateConverter.class
        })
public interface UserProfileMapper {

    @Mapping(target = "id", source = "userInfo.publicId")
    @Mapping(target = "contextUri", source = "userInfo.contextUri")
    @Mapping(target = "email", source = "userInfo.email")
    UserProfile toUserProfile(UserProfileEntity source);

}
