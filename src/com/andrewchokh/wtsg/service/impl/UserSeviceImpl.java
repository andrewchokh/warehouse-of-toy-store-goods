package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.ModelNotFoundException;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.service.contract.UserService;

public class UserSeviceImpl extends GenericService<User> implements UserService {

    private final UserRepository userRepository;

    public UserSeviceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User findByFullName(String fullName) {
        return userRepository.findByFullName(fullName).orElseThrow(
            () -> new ModelNotFoundException(
                MessageTemplate.MODEL_NOT_FOUND_BY.getTemplate().formatted("full name", fullName)));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
            () -> new ModelNotFoundException(
                MessageTemplate.MODEL_NOT_FOUND_BY.getTemplate().formatted("email", email)));
    }
}
