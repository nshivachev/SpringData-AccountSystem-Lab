package com.accountsystem.springdataaccountsystemlab.services;

import com.accountsystem.springdataaccountsystemlab.models.User;

public interface UserService {
    void registerUser(String username, int age);

    User findByUsername(String username);
}
