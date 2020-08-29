package com.rbc.vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashTest {

    private Cash cash;
    private String name;
    private Integer value;
    private Integer count;

    @BeforeEach
    void setUp() {
        cash = new Cash();
        name = "Cash Name";
        value = 10;
        count = 14;
    }

    @Test
    void setGetValue() {
        cash.setValue(value);
        assertEquals(value, cash.getValue());
    }

    @Test
    void setGetName() {
        cash.setName(name);
        assertEquals(name, cash.getName());
    }

    @Test
    void setGetCount() {
        cash.setCount(count);
        assertEquals(count, cash.getCount());
    }

    @Test
    void builder() {
        cash = Cash.builder().value(value).count(count).name(name).build();
        assertNotNull(cash);
        assertEquals(name, cash.getName());
        assertEquals(value, cash.getValue());
        assertEquals(count, cash.getCount());
    }
}