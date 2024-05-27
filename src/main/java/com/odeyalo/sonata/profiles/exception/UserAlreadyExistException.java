package com.odeyalo.sonata.profiles.exception;

/**
 * Exception thrown when an attempt is made to create a user that already exists.
 */
public final class UserAlreadyExistException extends RuntimeException {

    public static UserAlreadyExistException defaultException() {
        return new UserAlreadyExistException();
    }

    public static UserAlreadyExistException withCustomMessage(String message) {
        return new UserAlreadyExistException(message);
    }

    public static UserAlreadyExistException withMessageAndCause(String message, Throwable cause) {
        return new UserAlreadyExistException(message, cause);
    }

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
