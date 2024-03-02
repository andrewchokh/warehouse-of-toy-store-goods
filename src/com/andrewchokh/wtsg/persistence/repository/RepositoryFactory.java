package com.andrewchokh.wtsg.persistence.repository;

import com.andrewchokh.wtsg.persistence.exception.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.repository.contracts.ProductRepository;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.persistence.repository.contracts.WarehouseRepository;
import com.andrewchokh.wtsg.persistence.repository.impl.json.JsonRepositoryFactory;
import org.apache.commons.lang3.NotImplementedException;

public abstract class RepositoryFactory {

    public static final int JSON = 1;
    public static final int XML = 2;
    public static final int POSTGRESQL = 3;

    public static RepositoryFactory getRepositoryFactory(int factoryIndex)
        throws JsonFileIOException {
        return switch (factoryIndex) {
            case JSON -> JsonRepositoryFactory.getInstance();
            case XML ->
                throw new NotImplementedException("There are no XML support at the moment.");
            case POSTGRESQL -> throw new NotImplementedException(
                "The are no PostgreSQL support at the moment");
            default -> throw new IllegalArgumentException(
                "Invalid factory index.");
        };
    }

    public abstract ProductRepository getProductRepository();

    public abstract UserRepository getUserRepository();

    public abstract WarehouseRepository getWarehouseRepository();

    public abstract void commit() throws JsonFileIOException;
}
