package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.AuthException;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.service.contract.AuthService;
import org.mindrot.bcrypt.BCrypt;

final class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private User user;

    AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean authenticate(String email, String password) {
        User foundedUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException("Invalid email or password."));

        if (!BCrypt.checkpw(password, foundedUser.getPassword())) {
            return false;
        }

        user = foundedUser;
        return true;
    }

    @Override
    public Boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void logOut() {
        user = null;
    }
}
