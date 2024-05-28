package com.odeyalo.sonata.profiles.model.core;

import com.odeyalo.sonata.profiles.support.utils.TimeUtil;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * Wrapper around the birthdate.
 * <p>
 * Rules applied for birthdate:
 * <ul>
 *     <li>Cannot be in the future</li>
 * </ul>
 *
 * @param value - local date that represent the date of birth for the user
 */
public record Birthdate(@NotNull LocalDate value) {

    public Birthdate {
        if ( TimeUtil.isFuture(value) ) {
            throw new IllegalArgumentException("Invalid birthdate: Birthdate cannot be in the future.");
        }
    }

    public static Birthdate of(@NotNull LocalDate value) {
        return new Birthdate(value);
    }
}
