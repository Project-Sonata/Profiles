package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.entity.UserEntity;
import com.odeyalo.sonata.profiles.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = UserProfileMapper.class)
public interface UserMapper {

    User toUser(UserEntity entity);

}
