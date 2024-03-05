package com.andrewchokh.wtsg.persistence.repository.impl.json;

import com.andrewchokh.wtsg.exception.JsonFileIOException;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.model.impl.Warehouse;
import com.andrewchokh.wtsg.persistence.repository.contracts.WarehouseRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Optional;
import java.util.Set;

final class WarehouseJsonRepositoryImpl
    extends GenericJsonRepository<Warehouse>
    implements WarehouseRepository {

    WarehouseJsonRepositoryImpl(Gson gson) throws JsonFileIOException {
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
