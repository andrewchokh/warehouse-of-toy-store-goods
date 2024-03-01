package com.andrewchokh.wtsg;

import static java.lang.System.out;

import com.andrewchokh.wtsg.exceptions.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.models.impl.Product;
import com.andrewchokh.wtsg.persistence.repository.RepositoryFactory;
import com.andrewchokh.wtsg.persistence.repository.contracts.ProductRepository;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws JsonFileIOException {
        List<Product> products = generateProducts(10);

        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
            .getRepositoryFactory(RepositoryFactory.JSON);
        ProductRepository productRepository = jsonRepositoryFactory.getProductRepository();

        // Виведемо створених користувачів

        int i = 0;
        for (Product product : products) {
            productRepository.add(product);
            if (i == 3) {
                productRepository.remove(product);
            }
            if (i == 5) {
                productRepository.remove(product);
            }
            if (i == 7) {
                productRepository.remove(product);
            }
            i++;
        }

        productRepository.findAll().forEach(out::println);

        // Nessesary line! Must be in the end of main method.
        jsonRepositoryFactory.commit();
    }

    public static List<Product> generateProducts(int count) {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String name = faker.commerce().productName();
            BigDecimal price = BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100));
            double weight = faker.number().randomDouble(2, 0, 10);
            String image = faker.internet().image();

            Product product = new Product(UUID.randomUUID(), name, price, (float) weight, image);
            products.add(product);
        }

        for (Product product : products) {
            out.println(product);
        }

        return products;
    }
}