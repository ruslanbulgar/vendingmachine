package com.rbc.vendingmachine.repository;

import com.rbc.vendingmachine.model.ProductInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepository extends CrudRepository<ProductInfo, Integer> {
}

