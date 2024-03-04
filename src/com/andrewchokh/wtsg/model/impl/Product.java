package com.andrewchokh.wtsg.model.impl;

import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.ModelArgumentException;
import com.andrewchokh.wtsg.model.Model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * The Product class represents a product model in the system. This class extends the base Model
 * class. It contains fields to store product information such as name, price, weight, and image.
 * The price is represented in Ukrainian Hryvnia (UAH) and the weight is represented in kilograms
 * (KG). The image field is string that represent the product's image URL. The price is a BigDecimal
 * that represents the product's price. The weight is a Float that represents the product's weight.
 * This class also includes getter and setter methods for each field. The toString method is
 * overridden to provide a custom string representation of the Product object.
 *
 * @author andrewchokh
 */
public class Product extends Model {

    private String name;
    private BigDecimal price;
    private Float weight;
    private String image;

    public Product(UUID id, String name, BigDecimal price, Float weight, String image) {
        super(id);
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Boolean.TRUE.equals(validateName(name))) {
            this.name = name;
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (Boolean.TRUE.equals(validatePrice(price))) {
            this.price = price.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        if (Boolean.TRUE.equals(validateWeight(weight))) {
            this.weight = weight;
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if (Boolean.TRUE.equals(validateImage(image))) {
            this.image = image;
        }
    }

    public Boolean validateName(String name) {
        String argumentName = "Name";

        if (name.isBlank()) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    public Boolean validatePrice(BigDecimal price) {
        String argumentName = "Price";

        if (price.compareTo(BigDecimal.ZERO) == 0) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    public Boolean validateWeight(Float weight) {
        String argumentName = "Weight";

        if (weight.isInfinite() || weight < 0) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    public Boolean validateImage(String image) {
        //TODO: Implement functionality
        return true;
    }

    @Override
    public String toString() {
        return "Product{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", weight=" + weight +
            ", image='" + image + '\'' +
            '}';
    }
}
