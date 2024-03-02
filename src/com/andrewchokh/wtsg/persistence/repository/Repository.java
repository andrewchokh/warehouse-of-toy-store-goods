package com.andrewchokh.wtsg.persistence.repository;

import com.andrewchokh.wtsg.persistence.model.Model;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

public interface Repository<M extends Model> {

    Optional<M> findById(UUID id);

    Set<M> findAll();

    Set<M> findAll(Predicate<M> filter);

    M add(M model);

    boolean remove(M model);
}
