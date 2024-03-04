package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.ModelNotFoundException;
import com.andrewchokh.wtsg.model.impl.Product;
import com.andrewchokh.wtsg.persistence.repository.contracts.ProductRepository;
import com.andrewchokh.wtsg.service.contract.ProductService;

public class ProductServiceImpl extends GenericService<Product> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(
            () -> new ModelNotFoundException("The model has not been found by specified name."));
    }
}
