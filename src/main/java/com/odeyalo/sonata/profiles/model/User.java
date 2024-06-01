package com.odeyalo.sonata.profiles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@AllArgsConstructor
@Builder
public class User {
    @NotNull
    UserProfile profile;
}
