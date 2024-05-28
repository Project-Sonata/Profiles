package com.odeyalo.sonata.profiles.model.core;

import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;

public record UserId(@NotNull String value) {

    @NotNull
    public static UserId fromString(@NotNull final String userId) {
        return new UserId(userId);
    }

    /**
     * Utility factory method to create randomly-generated user ID
     * @return - randomly generated user id
     */
    @NotNull
    public static UserId random() {
        return UserId.fromString(
                RandomStringUtils.randomAlphanumeric(22)
        );
    }
}
