package com.andrewchokh.wtsg;

import static java.lang.System.out;

import com.andrewchokh.wtsg.persistence.exception.JsonFileIOException;
import com.andrewchokh.wtsg.persistence.model.impl.Product;
import com.andrewchokh.wtsg.persistence.model.impl.Role;
import com.andrewchokh.wtsg.persistence.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.RepositoryFactory;
import com.andrewchokh.wtsg.persistence.repository.contracts.ProductRepository;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws JsonFileIOException {
        List<Product> products = generateProducts(10);
        List<User> users = generateUsers(5);

        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
            .getRepositoryFactory(RepositoryFactory.JSON);
        ProductRepository productRepository = jsonRepositoryFactory.getProductRepository();
        UserRepository userRepository = jsonRepositoryFactory.getUserRepository();

        for (User user : users) {
            userRepository.add(user);
        }

        productRepository.findAll().forEach(out::println);

        //Nessesary line! Must be in the end of main method.
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

    public static List<User> generateUsers(int count) {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String password = faker.internet().password();
            String email = faker.internet().emailAddress();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Role role = Role.valueOf("ADMIN");

            User user = new User(UUID.randomUUID(), password, email, firstName, lastName, role);
            users.add(user);
        }

        return users;
    }
}