package com.andrewchokh.wtsg.exception;

public class SignUpException extends RuntimeException {

    public SignUpException(String message) {
        super(message);
    }

    public SignUpException(Throwable cause) {
        super("An error occurred during registration: %s".formatted(cause));
    }
}
