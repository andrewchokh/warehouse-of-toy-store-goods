package com.andrewchokh.wtsg.persistence.repository.impl.json;

import com.andrewchokh.wtsg.exceptions.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.models.Model;
import com.andrewchokh.wtsg.persistence.repository.RepositoryFactory;
import com.andrewchokh.wtsg.persistence.repository.contracts.ProductRepository;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.persistence.repository.contracts.WarehouseRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public class JsonRepositoryFactory extends RepositoryFactory {

    private final Gson gson;
    private ProductJsonRepositoryImpl productJsonRepositoryImpl;
    private UserJsonRepositoryImpl userJsonRepositoryImpl;
    private WarehouseJsonRepositoryImpl warehouseJsonRepositoryImpl;

    private JsonRepositoryFactory() throws JsonFileIOException {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gson = gsonBuilder.setPrettyPrinting().create();

        productJsonRepositoryImpl = new ProductJsonRepositoryImpl(gson);
        userJsonRepositoryImpl = new UserJsonRepositoryImpl(gson);
        warehouseJsonRepositoryImpl = new WarehouseJsonRepositoryImpl(gson);
    }

    public static JsonRepositoryFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public ProductRepository getProductRepository() {
        return productJsonRepositoryImpl;
    }

    @Override
    public UserRepository getUserRepository() {
        return userJsonRepositoryImpl;
    }

    @Override
    public WarehouseRepository getWarehouseRepository() {
        return warehouseJsonRepositoryImpl;
    }

    @Override
    public void commit() {
        try {
            serializeModels(productJsonRepositoryImpl.getPath(),
                productJsonRepositoryImpl.findAll());
            serializeModels(userJsonRepositoryImpl.getPath(),
                userJsonRepositoryImpl.findAll());
            serializeModels(warehouseJsonRepositoryImpl.getPath(),
                warehouseJsonRepositoryImpl.findAll());
        } catch (JsonFileIOException e) {
            throw new RuntimeException(e);
        }
    }

    private <M extends Model> void serializeModels(Path path, Set<M> models)
        throws JsonFileIOException {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(models, writer);
        } catch (IOException e) {
            throw new JsonFileIOException("Не вдалось зберегти дані у json-файл. Детальніше: %s"
                .formatted(e.getMessage()));
        }
    }

    private static class InstanceHolder {

        public static final JsonRepositoryFactory INSTANCE;

        static {
            try {
                INSTANCE = new JsonRepositoryFactory();
            } catch (JsonFileIOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
