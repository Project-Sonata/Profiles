package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.model.core.Email;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public class EmailConverter {

    public Email toEmail(String value) {
        return Email.of(value);
    }

    public String toString(Email email) {
        return email.value();
    }
}
