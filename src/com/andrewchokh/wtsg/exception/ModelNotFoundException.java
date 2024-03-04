package com.andrewchokh.wtsg.exception;

public class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException(String message) {
        super(!message.isBlank() ? message : "The model has not been found.");
    }
}
