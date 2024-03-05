package com.andrewchokh.wtsg.persistence.repository.impl.json;

import com.andrewchokh.wtsg.exception.JsonFileIOException;
import com.andrewchokh.wtsg.model.impl.Product;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.contracts.ProductRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Optional;
import java.util.Set;

final class ProductJsonRepositoryImpl
    extends GenericJsonRepository<Product>
    implements ProductRepository {

    ProductJsonRepositoryImpl(Gson gson) throws JsonFileIOException {
        super(gson, JsonPathFactory.PRODUCTS.getPath(), TypeToken
            .getParameterized(Set.class, User.class)
            .getType());
    }

    @Override
    public Optional<Product> findByName(String name) {
        return models.stream().filter(p -> p.getName().equals(name)).findFirst();
    }
}
