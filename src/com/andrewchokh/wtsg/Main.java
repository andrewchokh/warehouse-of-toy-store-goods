package com.andrewchokh.wtsg;

import com.andrewchokh.wtsg.persistence.models.impl.Product;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        generateProducts();
    }

    static void generateProducts() {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String name = faker.commerce().productName();
            BigDecimal price = BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100));
            double weight = faker.number().randomDouble(2, 0, 10);
            String image = faker.internet().image();

            Product product = new Product(UUID.randomUUID(), name, price, (float) weight, image);
            products.add(product);
        }

        for (Product product : products) {
            System.out.println(product);
        }

        writeToJson(products, "products.json");
    }

    static void writeToJson(List<?> objects, String pathToFile) {
        Gson gson = new Gson();
        String json = gson.toJson(objects);

        try (FileWriter file = new FileWriter(pathToFile)) {
            file.write(json);
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("JSON Object: " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}