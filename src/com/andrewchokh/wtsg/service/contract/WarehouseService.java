package com.andrewchokh.wtsg.service.contract;

import com.andrewchokh.wtsg.model.impl.Warehouse;

public interface WarehouseService {

    Warehouse findByName(String name);

    Warehouse findByAddress(String address);
}
