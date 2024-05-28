package com.odeyalo.sonata.profiles.support.validation;

import com.neovisionaries.i18n.CountryCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;

/**
 * {@link ConstraintValidator} that applied for {@link IsoCountryCode2} annotation and used to validate the String to be
 * <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code
 */
public final class IsoCountryCode2Validator implements ConstraintValidator<IsoCountryCode2, String> {

    @Override
    public boolean isValid(@NotNull final String isoAlpha2Code,
                           @NotNull final ConstraintValidatorContext context) {

        return CountryCode.getByAlpha2Code(isoAlpha2Code) != null;
    }
}
