package com.andrewchokh.wtsg.persistence.repository.impl.json;

import com.andrewchokh.wtsg.exception.JsonFileIOException;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Optional;
import java.util.Set;

public class UserJsonRepositoryImpl
    extends GenericJsonRepository<User>
    implements UserRepository {

    public UserJsonRepositoryImpl(Gson gson) throws JsonFileIOException {
        super(gson, JsonPathFactory.USERS.getPath(), TypeToken
            .getParameterized(Set.class, User.class)
            .getType());
    }

    @Override
    public Optional<User> findByFullName(String username) {
        return models.stream().filter(u -> u.getFullName().equals(username)).findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return models.stream().filter(u -> u.getFullName().equals(email)).findFirst();
    }
}
