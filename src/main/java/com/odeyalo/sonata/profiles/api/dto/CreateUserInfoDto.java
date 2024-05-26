package com.odeyalo.sonata.profiles.api.dto;

import com.odeyalo.sonata.profiles.model.Gender;
import com.odeyalo.sonata.profiles.support.validation.ValidBirthdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CreateUserInfoDto {
    @NotNull
    String id;
    @NotNull
    String username;
    @NotNull
    @ValidBirthdate
    LocalDate birthdate;
    @NotNull
    @Email
    String email;
    @NotNull
    Gender gender;
}
