package com.andrewchokh.wtsg.persistence.repository.impl.json;

import com.andrewchokh.wtsg.exceptions.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.models.impl.User;
import com.andrewchokh.wtsg.persistence.models.impl.Warehouse;
import com.andrewchokh.wtsg.persistence.repository.contracts.WarehouseRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Optional;
import java.util.Set;

public class WarehouseJsonRepositoryImpl
    extends GenericJsonRepository<Warehouse>
    implements WarehouseRepository {

    public WarehouseJsonRepositoryImpl(Gson gson) throws JsonFileIOException {
        super(gson, JsonPathFactory.WAREHOUSES.getPath(), TypeToken
            .getParameterized(Set.class, User.class)
            .getType());
    }

    @Override
    public Optional<Warehouse> findByName(String name) {
        return models.stream().filter(w -> w.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<Warehouse> findByAddress(String address) {
        return models.stream().filter(w -> w.getAddress().equals(address)).findFirst();
    }
}
