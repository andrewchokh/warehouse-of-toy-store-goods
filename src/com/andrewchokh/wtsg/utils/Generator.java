package com.andrewchokh.wtsg.utils;

import com.andrewchokh.wtsg.model.impl.Product;
import com.andrewchokh.wtsg.model.impl.User;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Generator {

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
            User.Role role = User.Role.valueOf("ADMIN");

            User user = new User(UUID.randomUUID(), password, email, firstName, lastName, role);
            users.add(user);
        }

        return users;
    }
}
