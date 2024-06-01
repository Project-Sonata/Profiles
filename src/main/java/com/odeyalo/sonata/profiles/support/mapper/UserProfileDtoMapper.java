package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.api.dto.UserProfileDto;
import com.odeyalo.sonata.profiles.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                UserIdConverter.class,
                EmailConverter.class,
                BirthdateConverter.class
        })
public interface UserProfileDtoMapper {

    @Mapping(target = "countryCode", source = "country")
    UserProfileDto toUserProfileDto(UserProfile profile);
}
