package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.ModelNotFoundException;
import com.andrewchokh.wtsg.model.Model;
import com.andrewchokh.wtsg.persistence.repository.Repository;
import com.andrewchokh.wtsg.service.Service;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

class GenericService<M extends Model> implements Service<M> {

    private final Repository repository;

    public GenericService(Repository repository) {
        this.repository = repository;
    }

    @Override
    public M get(UUID id) throws Throwable {
        return (M) repository.findById(id).orElseThrow(
            () -> new ModelNotFoundException(
                MessageTemplate.MODEL_NOT_FOUND_BY.getTemplate().formatted("ID", id)));
    }

    @Override
    public Set<M> getAll() {
        return repository.findAll();
    }

    @Override
    public Set<M> getAll(Predicate filter) {
        return repository.findAll(filter);
    }

    @Override
    public M add(Model model) {
        return (M) repository.add(model);
    }

    @Override
    public Boolean remove(Model model) {
        return repository.remove(model);
    }
}
