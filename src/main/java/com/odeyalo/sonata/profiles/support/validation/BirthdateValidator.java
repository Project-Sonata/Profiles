package com.odeyalo.sonata.profiles.support.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.BooleanUtils;

import java.time.LocalDate;

public final class BirthdateValidator implements ConstraintValidator<ValidBirthdate, LocalDate> {
    private static final int ALLOWED_MIN_AGE = 14;

    @Override
    public boolean isValid(final LocalDate value, final ConstraintValidatorContext context) {

        return BooleanUtils.negate(
                LocalDate.now().minusYears(ALLOWED_MIN_AGE).isBefore(value)
        );
    }
}
