package com.andrewchokh.wtsg.exception;

public enum MessageTemplate {
    MODEL_ARGUMENT("Incorrect field value: %s"),
    INVALID_VERIFICATION_CODE("Invalid verification code."),
    EXPIRED_VERIFICATION_CODE("Time of verification has passed.");

    private final String template;

    MessageTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
