package com.example.springdataintro.services;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal money, Integer id);

    void transferMoney(BigDecimal money, Integer id);
}
