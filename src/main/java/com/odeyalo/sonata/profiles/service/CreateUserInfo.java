package com.odeyalo.sonata.profiles.service;

import com.odeyalo.sonata.profiles.model.core.Email;
import com.odeyalo.sonata.profiles.model.core.UserId;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class CreateUserInfo {
    @NotNull
    UserId id;
    @NotNull
    Email email;
    @NotNull
    String countryCode;
}
