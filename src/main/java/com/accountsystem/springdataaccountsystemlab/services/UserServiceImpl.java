package com.accountsystem.springdataaccountsystemlab.services;

import com.accountsystem.springdataaccountsystemlab.models.Account;
import com.accountsystem.springdataaccountsystemlab.models.User;
import com.accountsystem.springdataaccountsystemlab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String username, int age) {
        if (username.isBlank() || age < 18) {
            throw new RuntimeException("Validation failed");
        }

        Optional<User> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        Account account = new Account();
        User user = new User(username, age, account);

        this.userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }
}
