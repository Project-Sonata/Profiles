package com.odeyalo.sonata.profiles.model.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BirthdateTest {

    @Test
    void birthdateCannotBeInFuture() {
        final LocalDate futureDate = LocalDate.now().plusDays(30);

        assertThatThrownBy(() -> new Birthdate(futureDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid birthdate: Birthdate cannot be in the future.");
    }

    @Test
    void birthdateThatGreaterThan150YearsIsNotAllowed() {
        final LocalDate exceedLimitBirthdate = LocalDate.now().minusYears(151);

        assertThatThrownBy(() -> new Birthdate(exceedLimitBirthdate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid birthdate: The provided birthdate exceeds the acceptable age limit of 150 years.");
    }

    @Test
    void birthdateCannotBeLessThan14YearsOld() {
        final LocalDate young = LocalDate.now().minusYears(12);

        assertThatThrownBy(() -> new Birthdate(young))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid birthdate: The provided birthdate indicates an age younger than the minimum acceptable age of 14 years.");
    }
}