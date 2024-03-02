package com.andrewchokh.wtsg.persistence.repository.impl.json;

import com.andrewchokh.wtsg.persistence.exception.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.model.Model;
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

    private static final JsonInstance instance = JsonInstance.INSTANCE;
    private final Gson gson;
    private final ProductJsonRepositoryImpl productJsonRepositoryImpl;
    private final UserJsonRepositoryImpl userJsonRepositoryImpl;
    private final WarehouseJsonRepositoryImpl warehouseJsonRepositoryImpl;

    private JsonRepositoryFactory() throws JsonFileIOException {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gson = gsonBuilder.setPrettyPrinting().create();

        productJsonRepositoryImpl = new ProductJsonRepositoryImpl(gson);
        userJsonRepositoryImpl = new UserJsonRepositoryImpl(gson);
        warehouseJsonRepositoryImpl = new WarehouseJsonRepositoryImpl(gson);
    }

    public static JsonRepositoryFactory getInstance() throws JsonFileIOException {
        if (instance.value == null) {
            instance.setValue(new JsonRepositoryFactory());
        }

        return instance.getValue();
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
    public void commit() throws JsonFileIOException {
        serializeModels(productJsonRepositoryImpl.getPath(),
            productJsonRepositoryImpl.findAll());
        serializeModels(userJsonRepositoryImpl.getPath(),
            userJsonRepositoryImpl.findAll());
        serializeModels(warehouseJsonRepositoryImpl.getPath(),
            warehouseJsonRepositoryImpl.findAll());
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
}
