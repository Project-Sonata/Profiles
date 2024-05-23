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
}