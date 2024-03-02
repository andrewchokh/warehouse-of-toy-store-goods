package com.andrewchokh.wtsg.persistence.repository.impl.json;

import com.andrewchokh.wtsg.persistence.exception.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.model.Model;
import com.andrewchokh.wtsg.persistence.repository.Repository;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class GenericJsonRepository<M extends Model> implements Repository<M> {

    protected final Set<M> models;
    private final Gson gson;
    private final Path path;
    private final Type collectionType;

    public GenericJsonRepository(Gson gson, Path path, Type collectionType)
        throws JsonFileIOException {
        this.gson = gson;
        this.path = path;
        this.collectionType = collectionType;
        this.models = new HashSet<>(loadAll());
    }

    @Override
    public Optional<M> findById(UUID id) {
        return models.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Set<M> findAll() {
        return models;
    }

    @Override
    public Set<M> findAll(Predicate<M> filter) {
        return models.stream().filter(filter).collect(Collectors.toSet());
    }

    @Override
    public M add(M model) {
        models.remove(model);
        models.add(model);
        return model;
    }

    @Override
    public boolean remove(M model) {
        return models.remove(model);
    }

    public Path getPath() {
        return path;
    }

    private Set<M> loadAll() throws JsonFileIOException {
        try {
            fileNotFound();
            var json = Files.readString(path);
            return isValidJson(json) ? gson.fromJson(json, collectionType) : new HashSet<>();
        } catch (IOException e) {
            throw new JsonFileIOException("Помилка при роботі із файлом %s."
                .formatted(path.getFileName()));
        }
    }

    private boolean isValidJson(String input) {
        try (JsonReader reader = new JsonReader(new StringReader(input))) {
            reader.skipValue();
            return reader.peek() == JsonToken.END_DOCUMENT;
        } catch (IOException e) {
            return false;
        }
    }

    private void fileNotFound() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}
