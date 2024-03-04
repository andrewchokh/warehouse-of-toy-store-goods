package com.andrewchokh.wtsg.exception;

/**
 * The {@code ModelArgumentException} class is a subclass of {@code IllegalArgumentException}. This
 * exception is thrown when a model argument is incorrectly specified.
 *
 * @author andrewchokh
 */

public class ModelArgumentException extends IllegalArgumentException {

    public ModelArgumentException(String message) {
        super(message);
    }
}
