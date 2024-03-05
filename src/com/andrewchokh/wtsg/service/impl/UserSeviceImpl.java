package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.dto.UserAddDTO;
import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.ModelNotFoundException;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.service.contract.UserService;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.function.Predicate;
import org.mindrot.bcrypt.BCrypt;

final class UserSeviceImpl extends GenericService<User> implements UserService {

    private final UserRepository userRepository;

    UserSeviceImpl(UserRepository userRepository) {
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

    @Override
    public User add(UserAddDTO userAddDTO) {
        String hashedPassword = BCrypt.hashpw(userAddDTO.getRawPassword(), BCrypt.gensalt());

        User user = new User(UUID.randomUUID(), hashedPassword, userAddDTO.getEmail(),
            userAddDTO.getFirstName(), userAddDTO.getLastName(), userAddDTO.getRole());

        userRepository.add(user);
        return user;
    }

    @Override
    public Set<User> getAll() {
        return getAll(u -> true);
    }

    @Override
    public Set<User> getAll(Predicate filter) {
        return new TreeSet<>(userRepository.findAll(filter));
    }
}
