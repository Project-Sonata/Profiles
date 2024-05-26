package com.odeyalo.sonata.profiles.support.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdateValidator.class)
@Documented
public @interface ValidBirthdate {

    String message() default "Birthdate is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
