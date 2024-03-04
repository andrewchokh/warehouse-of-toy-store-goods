package com.andrewchokh.wtsg.service.contract;

import com.andrewchokh.wtsg.model.impl.User;

public interface UserService {

    User findByFullName(String fullName);

    User findByEmail(String email);
}
