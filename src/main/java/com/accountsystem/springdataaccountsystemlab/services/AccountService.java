package com.accountsystem.springdataaccountsystemlab.services;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, Long id);

    void depositMoney(BigDecimal money, Long id);

    void transferMoney(Long accountFrom, Long accountTo, BigDecimal amount);
}
