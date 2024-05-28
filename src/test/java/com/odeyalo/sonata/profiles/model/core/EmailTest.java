package com.odeyalo.sonata.profiles.model.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailTest {

    @Test
    void shouldThrowExceptionIfEmailIsInvalid() {
        assertThatThrownBy(() -> Email.of("miku"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid email: Email address is not match the email address pattern");
    }

    @Test
    void shouldNotThrowAnyExceptionIfEmailIsCorrect() {
        assertDoesNotThrow(() -> Email.of("miku@gmail.com"));
    }
}