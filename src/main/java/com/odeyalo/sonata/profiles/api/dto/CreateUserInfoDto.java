package com.odeyalo.sonata.profiles.api.dto;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Value
@Builder
public class CreateUserInfoDto {
    @NotNull
    String id;
    @NotNull
    String username;
    @NotNull
    LocalDate birthdate;
    @NotNull
    String email;
}
