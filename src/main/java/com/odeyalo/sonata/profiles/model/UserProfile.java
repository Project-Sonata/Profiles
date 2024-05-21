package com.odeyalo.sonata.profiles.model;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

@Builder
public final class UserProfile {
    @NotNull
    String id;
}
