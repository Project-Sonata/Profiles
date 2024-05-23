package com.odeyalo.sonata.profiles.support.utils;

import java.time.LocalDate;

/**
 * A simple util functions to work with time
 */
public final class TimeUtil {
    /**
     * Indicates if the {@link LocalDate} is in the future
     * @param localDate - date to check
     * @return - true if {@link LocalDate} is in the future, false otherwise
     */
    public static boolean isFuture(final LocalDate localDate) {
        return LocalDate.now().isBefore(localDate);
    }
}
