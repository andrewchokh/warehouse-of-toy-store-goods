package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.AuthException;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import org.mindrot.bcrypt.BCrypt;

public class AuthService {

    private final UserRepository userRepository;
    private User user;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean authenticate(String email, String password) {
        User foundedUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException("Invalid email or password."));

        if (!BCrypt.checkpw(password, foundedUser.getPassword())) {
            return false;
        }

        user = foundedUser;
        return true;
    }

    public Boolean isAuthenticated() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void logOut() {
        user = null;
    }
}
