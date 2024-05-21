package com.odeyalo.sonata.profiles.model;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class UserProfile {
    @NotNull
    String id;
    @NotNull
    String displayName;
    @NotNull
    String country;
    @NotNull
    String email;
}
