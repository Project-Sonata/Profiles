package com.odeyalo.sonata.profiles.model.core;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

/**
 * Represent a name for the user.
 * <p>
 * Note that {@link Username}
 * and display name of the user are different and are not interchangeable.
 * <p>
 * Rules applied for username:
 * <ul>
 *     <li>Cannot be empty or blank</li>
 * </ul>
 * @param value a user's name
 */
public record Username(@NotNull String value) {

    public Username {
        Assert.hasText(value, () -> "A username cannot be empty!");
    }

    public static Username of(@NotNull final String value) {
        return new Username(value);
    }
}
