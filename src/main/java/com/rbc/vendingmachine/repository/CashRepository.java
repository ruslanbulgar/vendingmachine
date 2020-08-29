package com.rbc.vendingmachine.repository;

import com.rbc.vendingmachine.model.Cash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashRepository extends CrudRepository<Cash, Integer> {

    List<Cash> findByCountGreaterThan(Integer count);
}
