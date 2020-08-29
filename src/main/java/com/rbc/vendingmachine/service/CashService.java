package com.rbc.vendingmachine.service;

import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.repository.CashRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CashService {

    private final CashRepository cashRepository;

    public CashService(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    public Cash save(Cash cash) {
        return cashRepository.save(cash);
    }

    public void updateOrCreateCash(Integer value, String name, Integer count) {
        Cash cash = getCash(value);

        if (cash != null) {
            cash.setName(name);
            cash.setValue(value);
            cash.setCount(count);
        } else {
           cash = Cash.builder().value(value).name(name).count(count).build();
        }

        save(cash);
    }

    public Iterable<Cash> getAcceptedCash() {
        return cashRepository.findAll();
    }

    public Cash getCash(Integer value) {
        return cashRepository.findById(value).orElse(null);
    }

    public List<Cash> findAllCashNotEmptyCount() {
        List<Cash> result = cashRepository.findByCountGreaterThan(0);
        result.sort(Comparator.comparing(Cash::getValue).reversed());
        return result;
    }

    public void addCash(Integer value) {
        Cash cash = cashRepository.findById(value).orElse(null);
        if(cash != null) {
            cash.setCount(cash.getCount()+1);
            cashRepository.save(cash);
        }
    }

    public void returnCash(Integer value) {
        Cash cash = cashRepository.findById(value).orElse(null);
        if(cash != null) {
            cash.setCount(cash.getCount()-1);
            cashRepository.save(cash);
        }
    }

    public void removeCash(Integer code) {
        cashRepository.deleteById(code);
    }
}
