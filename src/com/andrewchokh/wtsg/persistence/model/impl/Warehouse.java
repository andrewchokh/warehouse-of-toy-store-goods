package com.andrewchokh.wtsg.persistence.model.impl;

import com.andrewchokh.wtsg.persistence.exception.MessageTemplate;
import com.andrewchokh.wtsg.persistence.exception.ModelArgumentException;
import com.andrewchokh.wtsg.persistence.model.Model;
import java.util.List;
import java.util.UUID;

/**
 * The Warehouse class represents a warehouse model in the system. This class extends the base Model
 * class. It contains fields to store warehouse information such as name, address, phone number, and
 * a list of available products. The name, address, and phone number fields are strings that
 * represent the warehouse's name, address, and phone number respectively. The availableProducts
 * field is a list of Product objects that represents the products available in the warehouse. This
 * class also includes getter and setter methods for each field except for availableProducts, which
 * can only be set during object creation. The toString method is overridden to provide a custom
 * string representation of the Warehouse object.
 *
 * @author andrewchokh
 */
public class Warehouse extends Model {

    private final List<Product> availableProducts;
    private String name;
    private String address;
    private String phoneNumber;

    public Warehouse(UUID id, String name, String address, String phoneNumber,
        List<Product> availableProducts) {
        super(id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.availableProducts = availableProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Boolean.TRUE.equals(validateName(name))) {
            this.name = name;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (Boolean.TRUE.equals(validateAddress(address))) {
            this.address = address;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (Boolean.TRUE.equals(validatePhoneNumber(phoneNumber))) {
            this.phoneNumber = phoneNumber;
        }
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public Boolean validateName(String name) {
        String argumentName = "Name";

        if (name.isBlank()) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    public Boolean validateAddress(String address) {
        String argumentName = "Address";

        if (address.isBlank()) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    public Boolean validatePhoneNumber(String phoneNumber) {
        String argumentName = "Phone Number";

        if (!phoneNumber.matches("^\\+?\\d{10,15}$")) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
            "name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", availableProducts=" + availableProducts +
            '}';
    }
}
