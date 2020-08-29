package com.rbc.vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductInfoTest {

    private ProductInfo productInfo;
    private Integer code;
    private String name;
    private Integer price;
    private Integer macProducts;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        productInfo = new ProductInfo();
        code = 10;
        name = "Snack Name";
        price = 50;
        macProducts = 3;
        products = getProducts();
    }

    private List<Product> getProducts() {
        return List.of(
                Product.builder().code(code).expirationDate(LocalDate.of(2020, 12, 12)).build(),
                Product.builder().code(code).expirationDate(LocalDate.of(2020, 12, 12)).build(),
                Product.builder().code(code).expirationDate(LocalDate.of(2020, 12, 12)).build());
    }

    @Test
    void setGetCode() {
        productInfo.setCode(code);
        assertEquals(code, productInfo.getCode());
    }

    @Test
    void setGetName() {
        productInfo.setName(name);
        assertEquals(name, productInfo.getName());
    }

    @Test
    void setGetProducts() {
        productInfo.setProducts(products);
        assertEquals(products, productInfo.getProducts());
    }

    @Test
    void setGetPrice() {
        productInfo.setPrice(price);
        assertEquals(price, productInfo.getPrice());
    }

    @Test
    void setGetMaxProducts() {
        productInfo.setMaxProducts(macProducts);
        assertEquals(macProducts, productInfo.getMaxProducts());
    }

    @Test
    void builder() {
        productInfo = ProductInfo.builder()
                .price(price)
                .name(name)
                .maxProducts(macProducts)
                .build();
        assertNotNull(productInfo);
        assertEquals(price, productInfo.getPrice());
        assertEquals(macProducts, productInfo.getMaxProducts());
        assertEquals(name, productInfo.getName());
    }
}