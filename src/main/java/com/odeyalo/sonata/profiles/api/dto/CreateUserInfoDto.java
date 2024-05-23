package com.odeyalo.sonata.profiles.api.dto;

import com.odeyalo.sonata.profiles.model.core.Birthdate;
import com.odeyalo.sonata.profiles.model.core.Username;
import org.jetbrains.annotations.NotNull;

public final class CreateUserInfoDto {
    @NotNull
    Username username;
    @NotNull
    Birthdate birthdate;
}
