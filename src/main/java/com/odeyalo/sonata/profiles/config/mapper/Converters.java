package com.odeyalo.sonata.profiles.config.mapper;

import com.odeyalo.sonata.profiles.support.mapper.UserProfileMapper;
import com.odeyalo.sonata.profiles.support.mapper.UserProfileMapperImpl;

/**
 * Converters to map POJOs, primary used for unit tests.
 * <p>
 * Not marked as Spring configuration because Mapstruct
 * automatically annotate mappers with {@link org.springframework.stereotype.Component} annotation
 */
public class Converters {

    public UserProfileMapper userProfileMapper() {
        return new UserProfileMapperImpl();
    }
}
