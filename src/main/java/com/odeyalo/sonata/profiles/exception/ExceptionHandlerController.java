package com.odeyalo.sonata.profiles.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class ExceptionHandlerController {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Void> userAlreadyExistExceptionHandler(final UserAlreadyExistException ex) {
        return ResponseEntity.badRequest().build();
    }
}
