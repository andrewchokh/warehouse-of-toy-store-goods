package com.andrewchokh.wtsg.persistence.repository.contracts;

import com.andrewchokh.wtsg.persistence.models.impl.Product;
import com.andrewchokh.wtsg.persistence.repository.Repository;
import java.util.Optional;

public interface ProductRepository extends Repository<Product> {

    Optional<Product> findByName(String name);
}
