package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.DependencyException;
import com.andrewchokh.wtsg.persistence.repository.RepositoryFactory;
import com.andrewchokh.wtsg.persistence.repository.contracts.ProductRepository;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.persistence.repository.contracts.WarehouseRepository;
import com.andrewchokh.wtsg.service.contract.AuthService;
import com.andrewchokh.wtsg.service.contract.ProductService;
import com.andrewchokh.wtsg.service.contract.SignUpService;
import com.andrewchokh.wtsg.service.contract.UserService;
import com.andrewchokh.wtsg.service.contract.WarehouseService;

public final class ServiceFactory {

    private static volatile ServiceFactory INSTANCE;

    private final RepositoryFactory repositoryFactory;
    private final ProductService productService;
    private final UserService userService;
    private final WarehouseService warehouseService;
    private final AuthService authService;
    private final SignUpService signUpService;

    private ServiceFactory(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;

        ProductRepository productRepository = repositoryFactory.getProductRepository();
        UserRepository userRepository = repositoryFactory.getUserRepository();
        WarehouseRepository warehouseRepository = repositoryFactory.getWarehouseRepository();

        productService = new ProductServiceImpl(productRepository);
        userService = new UserSeviceImpl(userRepository);
        warehouseService = new WarehouseServiceImpl(warehouseRepository);
        authService = new AuthServiceImpl(userRepository);
        signUpService = new SignUpServiceImpl(userService);
    }

    public static ServiceFactory getInstance() {
        if (INSTANCE.repositoryFactory != null) {
            return INSTANCE;
        } else {
            throw new DependencyException("");
        }
    }

    public static ServiceFactory getInstance(RepositoryFactory repositoryFactory) {
        if (INSTANCE == null) {
            synchronized (ServiceFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceFactory(repositoryFactory);
                }
            }
        }

        return INSTANCE;
    }

    public RepositoryFactory getRepositoryFactory() {
        return repositoryFactory;
    }

    public ProductService getProductService() {
        return productService;
    }

    public UserService getUserService() {
        return userService;
    }

    public WarehouseService getWarehouseService() {
        return warehouseService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public SignUpService getSignUpService() {
        return signUpService;
    }
}
