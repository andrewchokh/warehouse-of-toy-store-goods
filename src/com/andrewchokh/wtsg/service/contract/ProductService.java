package com.andrewchokh.wtsg.service.contract;

import com.andrewchokh.wtsg.model.impl.Product;
import com.andrewchokh.wtsg.service.Service;

public interface ProductService extends Service<Product> {

    Product findByName(String name);
}
