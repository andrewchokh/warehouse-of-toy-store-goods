package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.ModelNotFoundException;
import com.andrewchokh.wtsg.model.impl.Warehouse;
import com.andrewchokh.wtsg.persistence.repository.contracts.WarehouseRepository;
import com.andrewchokh.wtsg.service.contract.WarehouseService;

public class WarehouseServiceImpl extends GenericService<Warehouse> implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        super(warehouseRepository);
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse findByName(String name) {
        return warehouseRepository.findByName(name).orElseThrow(
            () -> new ModelNotFoundException(
                MessageTemplate.MODEL_NOT_FOUND_BY.getTemplate().formatted("name", name)));
    }

    @Override
    public Warehouse findByAddress(String address) {
        return warehouseRepository.findByName(address).orElseThrow(
            () -> new ModelNotFoundException(
                MessageTemplate.MODEL_NOT_FOUND_BY.getTemplate().formatted("address", address)));
    }
}
