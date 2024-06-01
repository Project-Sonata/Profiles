package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.model.User;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        injectionStrategy = CONSTRUCTOR,
        uses = UserProfileMapper.class)
public interface UserMapper {

    User toUser(UserEntity entity);

}
