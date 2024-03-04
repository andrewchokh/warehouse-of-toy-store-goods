package com.andrewchokh.wtsg.service;

import com.andrewchokh.wtsg.model.Model;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

public interface Service<M extends Model> {

    M get(UUID id) throws Throwable;

    Set<M> getAll();

    Set<M> getAll(Predicate<M> filter);

    M add(M model);

    Boolean remove(M model);
}
