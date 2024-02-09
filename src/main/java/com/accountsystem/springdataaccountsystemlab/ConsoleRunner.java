package com.accountsystem.springdataaccountsystemlab;

import com.accountsystem.springdataaccountsystemlab.models.User;
import com.accountsystem.springdataaccountsystemlab.services.AccountService;
import com.accountsystem.springdataaccountsystemlab.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userService.registerUser("First", 18);
        User user = userService.findByUsername("First");
        this.accountService.depositMoney(BigDecimal.TEN, user.getAccountIds().get(0));
        this.accountService.withdrawMoney(BigDecimal.ONE, user.getAccountIds().get(0));
    }
}
