package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.entity.UserProfileEntity;
import com.odeyalo.sonata.profiles.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "id", source = "userInfo.publicId")
    UserProfile toUserProfile(UserProfileEntity source);

}
