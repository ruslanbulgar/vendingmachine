package com.rbc.vendingmachine.service;

import com.rbc.vendingmachine.repository.ProductInfoRepository;
import com.rbc.vendingmachine.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


}

