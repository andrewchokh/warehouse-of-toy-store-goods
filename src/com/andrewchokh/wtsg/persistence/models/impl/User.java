package com.andrewchokh.wtsg.persistence.models.impl;

import com.andrewchokh.wtsg.exceptions.MessageTemplate;
import com.andrewchokh.wtsg.exceptions.ModelArgumentException;
import com.andrewchokh.wtsg.persistence.models.Model;
import java.util.UUID;

/**
 * The User class represents a user model in the system. This class extends the base Model class. It
 * contains fields to store user information such as password, email, first name, and last name. The
 * password and email fields are final and can only be set during object creation. The first name
 * and last name fields can be updated after the object is created. This class also includes methods
 * for validating the password, email, first name, and last name. The toString method is overridden
 * to provide a custom string representation of the User object.
 *
 * @author andrewchokh
 */
public class User extends Model {

    private final String password;
    private final String email;
    private String firstName;
    private String lastName;

    public User(UUID id, String password, String email, String firstName, String lastName) {
        super(id);
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (Boolean.TRUE.equals(validateFirstName(firstName))) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (Boolean.TRUE.equals(validateLastName(lastName))) {
            this.lastName = lastName;
        }
    }

    public boolean validatePassword(String password) {
        String argumentName = "Password";

        if (password == null || password.isEmpty()) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        if (password.length() < 8 || password.length() > 24) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        // Check if password contains only Latin symbols
        for (char c : password.toCharArray()) {
            if (!Character.isLetter(c) || !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                throw new ModelArgumentException(
                    MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
            }
        }

        return true;
    }

    public Boolean validateEmail() {
        // TODO: Implement functionality
        return true;
    }

    public Boolean validateFirstName(String firstName) {
        String argumentName = "First Name";

        if (firstName.isBlank()) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    public Boolean validateLastName(String lastName) {
        String argumentName = "Last Name";

        if (lastName.isBlank()) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
            "password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
    }
}
