package com.odeyalo.sonata.profiles.api.dto;

import com.odeyalo.sonata.profiles.model.core.Birthdate;
import com.odeyalo.sonata.profiles.model.core.Email;
import com.odeyalo.sonata.profiles.model.core.Username;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class CreateUserInfoDto {
    @NotNull
    Username username;
    @NotNull
    Birthdate birthdate;
    @NotNull
    Email email;
}
