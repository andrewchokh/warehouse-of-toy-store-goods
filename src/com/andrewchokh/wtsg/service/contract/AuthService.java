package com.andrewchokh.wtsg.service.contract;

import com.andrewchokh.wtsg.model.impl.User;

public interface AuthService {

    public Boolean authenticate(String email, String password);

    public Boolean isAuthenticated();

    public User getUser();

    public void logOut();
}
