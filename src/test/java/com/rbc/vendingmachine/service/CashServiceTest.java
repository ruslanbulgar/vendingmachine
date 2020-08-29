package com.rbc.vendingmachine.service;

import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.repository.CashRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CashServiceTest {

    @Autowired
    CashRepository cashRepository;

    CashService cashService;
    private Cash cash;

    @BeforeEach
    void setUp() {
        cashService = new CashService(cashRepository);
        cash = Cash.builder().value(1).name("Penny").count(0).build();
    }

    @Test
    void save() {
        Cash savedCash = cashService.save(cash);
        assertNotNull(savedCash);
        assertEquals(cash.getValue(), savedCash.getValue());
    }

    @Test
    void updateOrCreateCash_update() {
        cashRepository.save(cash);

        cashService.updateOrCreateCash(cash.getValue(), cash.getName(), 3);

        Cash cashById = cashRepository.findById(cash.getValue()).orElse(null);

        assertNotNull(cashById);
        assertEquals(3, cashById.getCount());
    }

    @Test
    void updateOrCreateCash_create() {
        cashService.updateOrCreateCash(cash.getValue(), cash.getName(), 3);

        Cash cashById = cashRepository.findById(cash.getValue()).orElse(null);

        assertNotNull(cashById);
        assertEquals(3, cashById.getCount());
    }

    @Test
    void getAcceptedCash() {
        cashRepository.save(cash);
        Iterable<Cash> acceptedCash = cashService.getAcceptedCash();
        assertEquals(cash, acceptedCash.iterator().next());
    }

    @Test
    void getCash_thatExist() {
        cashRepository.save(cash);
        Cash returnedCash = cashService.getCash(cash.getValue());
        assertNotNull(returnedCash);
        assertEquals(cash.getValue(), returnedCash.getValue());
    }

    @Test
    void getCash_thatNotExist() {
        Cash returnedCash = cashService.getCash(cash.getValue());
        assertNull(returnedCash);
    }

    @Test
    void findAllCashNotEmptyCount() {
        cashRepository.save(cash);
        cashRepository.save(Cash.builder().value(12).name("").count(2).build());
        List<Cash> returnedCash = cashService.findAllCashNotEmptyCount();
        assertNotNull(returnedCash);
        assertEquals(1, returnedCash.size());
    }

    @Test
    void addCash() {
        cashRepository.save(cash);
        cashService.addCash(cash.getValue());
        Cash returnedCash = cashService.getCash(cash.getValue());
        assertNotNull(returnedCash);
        assertEquals(1, returnedCash.getCount());
    }

    @Test
    void returnCash() {
        cash.setCount(2);
        cashRepository.save(cash);
        cashService.returnCash(cash.getValue());
        Cash returnedCash = cashService.getCash(cash.getValue());
        assertNotNull(returnedCash);
        assertEquals(1, returnedCash.getCount());
    }

    @Test
    void removeCash() {
        cashRepository.save(cash);
        cashService.removeCash(cash.getValue());
        Cash returnedCash = cashService.getCash(cash.getValue());
        assertNull(returnedCash);
    }
}