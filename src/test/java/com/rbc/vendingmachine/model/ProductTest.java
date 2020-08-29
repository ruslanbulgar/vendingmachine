package com.rbc.vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product product;
    private Integer code;
    private LocalDate expirationDate;
    private Integer id;

    @BeforeEach
    void setUp() {
        product = new Product();
        id = 1;
        code = 10;
        expirationDate = LocalDate.of(2020, 12, 13);
    }

    @Test
    void setGetId() {
        product.setId(id);
        assertEquals(id, product.getId());
    }

    @Test
    void setGetCode() {
        product.setCode(code);
        assertEquals(code, product.getCode());
    }

    @Test
    void setGetExpirationDate() {
        product.setExpirationDate(expirationDate);
        assertEquals(expirationDate, product.getExpirationDate());
    }

    @Test
    void builder() {
        product = Product.builder().code(code).expirationDate(expirationDate).build();
        assertNotNull(product);
        assertEquals(code, product.getCode());
        assertEquals(expirationDate, product.getExpirationDate());
    }
}