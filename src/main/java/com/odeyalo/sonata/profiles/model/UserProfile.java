package com.odeyalo.sonata.profiles.model;

import com.odeyalo.sonata.profiles.model.core.Birthdate;
import com.odeyalo.sonata.profiles.model.core.Email;
import com.odeyalo.sonata.profiles.model.core.UserId;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class UserProfile {
    @NotNull
    UserId id;
    @NotNull
    String displayName;
    @NotNull
    String country;
    @NotNull
    Email email;
    @NotNull
    Birthdate birthdate;
    @NotNull
    String contextUri;
    @NotNull
    Gender gender;
}
