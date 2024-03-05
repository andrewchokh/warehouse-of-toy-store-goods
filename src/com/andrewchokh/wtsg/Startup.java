package com.andrewchokh.wtsg;

import com.andrewchokh.wtsg.exception.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.repository.RepositoryFactory;
import com.andrewchokh.wtsg.utils.ApplicationLogger;

final class Startup {

    static void init() {
        ApplicationLogger.init();

        RepositoryFactory repositoryFactory;

        try {
            repositoryFactory = RepositoryFactory.getRepositoryFactory(RepositoryFactory.JSON);
        } catch (JsonFileIOException e) {
            ApplicationLogger.warning("%s: %s".formatted(e.getClass(), e.getMessage()));
        }

    }
}
