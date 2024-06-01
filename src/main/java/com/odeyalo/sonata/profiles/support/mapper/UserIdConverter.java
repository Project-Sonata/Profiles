package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.model.core.UserId;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public class UserIdConverter {

    public UserId toUserId(String idValue) {
        return UserId.fromString(idValue);
    }

    public String toString(UserId user) {
        return user.value();
    }
}
