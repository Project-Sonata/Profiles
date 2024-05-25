package com.odeyalo.sonata.profiles.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CreateUserInfoDto {
    String id;
    String username;
    LocalDate birthdate;
    @NotNull
    @Email
    String email;
}
