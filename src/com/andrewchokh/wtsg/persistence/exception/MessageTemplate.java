package com.andrewchokh.wtsg.persistence.exception;

public enum MessageTemplate {
    MODEL_ARGUMENT("Incorrect field value: %s");

    private final String template;

    MessageTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
