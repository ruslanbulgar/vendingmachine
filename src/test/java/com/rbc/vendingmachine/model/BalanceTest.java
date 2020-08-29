package com.rbc.vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {

    Balance balance;
    Integer value;
    Integer balanceValue;

    @BeforeEach
    void setUp() {
        balance = new Balance();

        value = 0;
        balanceValue = 100;
    }

    @Test
    void setGetId() {
        balance.setId(value);
        assertEquals(value, balance.getId());
    }

    @Test
    void setGetBalance() {

        balance.setBalance(balanceValue);
        assertEquals(balanceValue, balance.getBalance());

    }

    @Test
    void builder() {
        balance = Balance.builder().id(value).balance(balanceValue).build();
        assertNotNull(balance);
        assertEquals(value, balance.getId());
        assertEquals(balanceValue, balance.getBalance());
    }
}