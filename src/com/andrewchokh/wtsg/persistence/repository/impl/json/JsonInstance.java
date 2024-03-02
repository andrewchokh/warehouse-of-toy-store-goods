package com.andrewchokh.wtsg.persistence.repository.impl.json;

public enum JsonInstance {
    INSTANCE;

    JsonRepositoryFactory value;

    public JsonRepositoryFactory getValue() {
        return value;
    }

    public void setValue(JsonRepositoryFactory value) {
        this.value = value;
    }
}
