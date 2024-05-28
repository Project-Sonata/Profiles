package com.odeyalo.sonata.profiles.support.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * The {@code @IsoCountryCode2} annotation is used to validate that the annotated field is a valid ISO 3166-1 alpha-2 country code.
 * <p>
 * This annotation should be applied to fields of type {@code String}.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * public class UserProfile {
 *
 *     @IsoCountryCode2
 *     private String countryCode;
 *
 *     // Getters and Setters
 * }
 * }
 * </pre>
 * </p>
 * <p>
 * The validation is performed by the {@link IsoCountryCode2Validator} class.
 * </p>
 *
 * @see IsoCountryCode2Validator
 * @see jakarta.validation.Constraint
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsoCountryCode2Validator.class)
@Documented
public @interface IsoCountryCode2 {

    String message() default "Invalid ISO 3166-1 alpha-2 country code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
