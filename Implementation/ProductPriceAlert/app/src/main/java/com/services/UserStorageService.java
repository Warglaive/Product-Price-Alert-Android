package com.services;

import com.models.User;

/**
 * CRUD and more for the User
 */
public class UserStorageService {
    private User user;

    public UserStorageService() {

    }

    void registerUser(User user) {
        this.user = user;
        //do LB4 to register

    }

    public User getUser() {
        return user;
    }
}
