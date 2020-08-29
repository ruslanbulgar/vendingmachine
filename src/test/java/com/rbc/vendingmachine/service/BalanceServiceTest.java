package com.rbc.vendingmachine.service;

import com.rbc.vendingmachine.model.Balance;
import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.repository.BalanceRepository;
import com.rbc.vendingmachine.repository.CashRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BalanceServiceTest {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    CashRepository cashRepository;

    CashService cashService;


    BalanceService balanceService;

    Balance balanceObject;
    Integer id;
    Integer balance;

    @BeforeEach
    void setUp() {
        cashService = new CashService(cashRepository);
        balanceService = new BalanceService(balanceRepository, cashService);

        id = 0;
        balance = 5;
        balanceObject = Balance.builder().id(id).balance(balance).build();

    }

    @Test
    void getBalanceObject_whenExistInDB() {
        balanceRepository.save(balanceObject);
        Balance balance = balanceService.getBalanceObject();
        assertNotNull(balance);
        assertEquals(5, balance.getBalance());
    }

    @Test
    void getBalanceObject_whenNotExistInDB() {
        Balance balance = balanceService.getBalanceObject();
        assertNotNull(balance);
        assertEquals(0, balance.getBalance());
    }

    @Test
    void getBalance() {
        Integer balance = balanceService.getBalance();
        assertNotNull(balance);
        assertEquals(0, balance);
    }

    @Test
    void save() {
        Balance savedBalance = balanceService.save(balanceObject);
        assertNotNull(savedBalance);
        assertEquals(5, savedBalance.getBalance());
    }

    @Test
    void add() {
        Integer balance = balanceService.add(10);
        assertEquals(10, balance);
    }

    @Test
    void reduce() {
        balanceRepository.save(balanceObject);
        Integer balance = balanceService.reduce(2);
        assertEquals(3, balance);
    }

    @Test
    void returnChange() {
        getCashList().forEach((c)-> cashService.save(c));
        balanceObject.setBalance(6);
        balanceRepository.save(balanceObject);
        List<Cash> change = balanceService.returnChange();
        assertNotNull(change);
        assertEquals(2, change.size());
    }

    private List<Cash> getCashList() {
        List<Cash> cash = new ArrayList<>();
        cash.add(Cash.builder().value(1).name("Penny").count(10).build());
        cash.add(Cash.builder().value(5).name("Nickel").count(10).build());
        cash.add(Cash.builder().value(10).name("Dime").count(10).build());
        cash.add(Cash.builder().value(25).name("Quarter").count(10).build());
        cash.add(Cash.builder().value(50).name("Half").count(10).build());
        cash.sort(Comparator.comparing(Cash::getValue).reversed());
        return cash;
    }
}