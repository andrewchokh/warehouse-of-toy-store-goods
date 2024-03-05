package com.andrewchokh.wtsg.model.impl;

import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.ModelArgumentException;
import com.andrewchokh.wtsg.model.Model;
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
    private Role role;

    public User(UUID id, String password, String email, String firstName, String lastName,
        Role role) {
        super(id);
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Boolean validateFirstName(String firstName) {
        String argumentName = "First Name";

        if (firstName.isBlank()) {
            throw new ModelArgumentException(
                MessageTemplate.MODEL_ARGUMENT.getTemplate().formatted(argumentName));
        }

        return true;
    }

    private Boolean validateLastName(String lastName) {
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
            ", role=" + role +
            '}';
    }

    public enum Role {
        ADMIN("Admin", new Permission(true, true, true, true)),
        DEFAULT("Default", new Permission(false, true, false, false));

        private final String name;
        private final Permission permissions;

        Role(String name, Permission permissions) {
            this.name = name;
            this.permissions = permissions;
        }

        private record Permission(boolean canCreate, boolean canRead, boolean canUpdate,
                                  boolean canDelete) {

        }
    }
}
