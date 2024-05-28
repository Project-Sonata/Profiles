package com.odeyalo.sonata.profiles.model.core;

import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;

/**
 * Represent the email object
 *
 * @param value - valid email address
 */
public record Email(@NotNull String value) {

    public static Email of(final String value) {
        return new Email(value);
    }
    public Email {
        if ( !EmailValidator.getInstance().isValid(value) ) {
            throw new IllegalArgumentException("Invalid email: Email address is not match the email address pattern");
        }
    }
}
