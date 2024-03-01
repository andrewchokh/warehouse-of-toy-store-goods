package com.andrewchokh.wtsg.persistence.repository.contracts;

import com.andrewchokh.wtsg.persistence.models.impl.User;
import com.andrewchokh.wtsg.persistence.repository.Repository;
import java.util.Optional;

public interface UserRepository extends Repository<User> {

    Optional<User> findByFullName(String fullName);

    Optional<User> findByEmail(String email);
}
