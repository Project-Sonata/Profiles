package com.odeyalo.sonata.profiles.support.mapper;

import com.odeyalo.sonata.profiles.model.core.Birthdate;
import org.mapstruct.Mapper;

import java.time.LocalDate;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public class BirthdateConverter {

    public Birthdate toBirthdate(LocalDate value) {
        return Birthdate.of(value);
    }

    public LocalDate toLocalDate(Birthdate birthdate) {
        return birthdate.value();
    }
}
