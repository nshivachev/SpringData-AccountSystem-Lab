package com.accountsystem.springdataaccountsystemlab.services;

import com.accountsystem.springdataaccountsystemlab.exceptions.EntityMissingException;
import com.accountsystem.springdataaccountsystemlab.models.Account;
import com.accountsystem.springdataaccountsystemlab.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new EntityMissingException("Account does not exist");
        }

        BigDecimal currentBalance = account.get().getBalance();

        if (money.compareTo(currentBalance) > 0) {
            throw new RuntimeException("Cannot withdraw!");
        }

        account.get().setBalance(currentBalance.subtract(money));

        this.accountRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sorry no account"));

        BigDecimal currentBalance = account.getBalance();

        account.setBalance(currentBalance.add(money));

        this.accountRepository.save(account);
    }

    @Override
    @Transactional
    public void transferMoney(Long accountFrom, Long accountTo, BigDecimal money) {
        withdrawMoney(money, accountFrom);
        depositMoney(money, accountTo);
    }
}
