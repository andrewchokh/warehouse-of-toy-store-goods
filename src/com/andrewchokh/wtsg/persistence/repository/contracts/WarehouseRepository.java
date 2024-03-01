package com.andrewchokh.wtsg.persistence.repository.contracts;

import com.andrewchokh.wtsg.persistence.models.impl.Warehouse;
import com.andrewchokh.wtsg.persistence.repository.Repository;
import java.util.Optional;

public interface WarehouseRepository extends Repository<Warehouse> {

    Optional<Warehouse> findByName(String name);

    Optional<Warehouse> findByAddress(String address);
}
