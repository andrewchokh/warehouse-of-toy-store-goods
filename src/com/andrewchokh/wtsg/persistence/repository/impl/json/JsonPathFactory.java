package com.andrewchokh.wtsg.persistence.repository.impl.json;

import java.nio.file.Path;

enum JsonPathFactory {
    PRODUCTS("products.json"),
    USERS("users.json"),
    WAREHOUSES("warehouses.json");

    private static final String DATA_DIRECTORY = "data";
    private final String fileName;

    JsonPathFactory(String fileName) {
        this.fileName = fileName;
    }

    public Path getPath() {
        return Path.of(DATA_DIRECTORY, this.fileName);
    }
}
