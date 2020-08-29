package com.rbc.vendingmachine.repository;

import com.rbc.vendingmachine.model.Balance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Integer> {
}
