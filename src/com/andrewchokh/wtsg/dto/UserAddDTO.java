package com.andrewchokh.wtsg.dto;

import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.ModelArgumentException;
import com.andrewchokh.wtsg.model.Model;
import com.andrewchokh.wtsg.model.impl.User.Role;
import java.util.UUID;

public class UserAddDTO extends Model {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String rawPassword;
    private final String email;
    private final Role role;

    public UserAddDTO(UUID id, String firstName, String lastName, String username,
        String rawPassword, String email, Role role) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.rawPassword = rawPassword;
        this.email = email;
        this.role = role;

        validatePassword(rawPassword);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Boolean validatePassword(String password) {
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
}
