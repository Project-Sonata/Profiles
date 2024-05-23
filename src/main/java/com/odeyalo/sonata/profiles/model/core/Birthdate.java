package com.odeyalo.sonata.profiles.model.core;

import com.odeyalo.sonata.profiles.support.utils.TimeUtil;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * Represent the birthdate of the user.
 * <p>
 * Rules applied for username:
 * <ul>
 *     <li>Cannot be in the future</li>
 *     <li>Allowed minimal age is 14</li>
 *     <li>Allowed minimal age is 150</li>
 * </ul>
 *
 * @param value - local date that represent the date of birth for the user
 */
public record Birthdate(@NotNull LocalDate value) {
    private static final int ALLOWED_MIN_AGE = 14;
    private static final int ALLOWED_MAX_AGE = 150;

    public Birthdate {
        if ( TimeUtil.isFuture(value) ) {
            throw new IllegalArgumentException("Invalid birthdate: Birthdate cannot be in the future.");
        }

        if ( LocalDate.now().minusYears(ALLOWED_MAX_AGE).isAfter(value) ) {
            throw new IllegalArgumentException("Invalid birthdate: The provided birthdate exceeds the acceptable age limit of 150 years.");
        }

        if ( LocalDate.now().minusYears(ALLOWED_MIN_AGE).isBefore(value) ) {
            throw new IllegalArgumentException("Invalid birthdate: The provided birthdate indicates an age younger than the minimum acceptable age of 14 years.");
        }
    }
}
