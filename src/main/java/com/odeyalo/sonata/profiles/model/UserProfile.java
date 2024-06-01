package com.odeyalo.sonata.profiles.model;

import com.odeyalo.sonata.profiles.model.core.UserId;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

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
    String email;
    @NotNull
    LocalDate birthdate;
    @NotNull
    String contextUri;
    @NotNull
    Gender gender;
}
