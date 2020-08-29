package com.rbc.vendingmachine.service;

import com.rbc.vendingmachine.model.Balance;
import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final CashService cashService;

    public BalanceService(BalanceRepository balanceRepository, CashService cashService) {
        this.balanceRepository = balanceRepository;
        this.cashService = cashService;
    }

    public Balance getBalanceObject() {
        Balance currentBalance = balanceRepository.findById(0).orElse(null);

        if (currentBalance == null)
            currentBalance = balanceRepository.save(Balance.builder().id(0).balance(0).build());

        return currentBalance;
    }

    public Integer getBalance() {
        Balance currentBalance = getBalanceObject();

        return currentBalance.getBalance();
    }

    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    public Integer add(Integer coin) {
        Balance currentBalance = getBalanceObject();
        currentBalance.setBalance(currentBalance.getBalance() + coin);
        currentBalance = balanceRepository.save(currentBalance);

        return currentBalance.getBalance();
    }

    public Integer reduce(Integer coin) {
        Balance currentBalance = getBalanceObject();
        currentBalance.setBalance(currentBalance.getBalance() - coin);
        currentBalance = balanceRepository.save(currentBalance);

        return currentBalance.getBalance();
    }

    public List<Cash> returnChange() {

        if (getBalance() == 0)
            return new ArrayList<>();

        return calculateAndGetChangeList();
    }

    private List<Cash> calculateAndGetChangeList() {
        List<Cash> change = new ArrayList<>();
        for (Cash cash : cashService.findAllCashNotEmptyCount()) {
            change.addAll(extractBalanceFromCash(getBalance(), cash));
        }
        return change;
    }

    private List<Cash> extractBalanceFromCash(Integer balance, Cash cash) {
        List<Cash> change = new ArrayList<>();
        if ((balance - cash.getValue()) >= 0) {
            int i = balance / cash.getValue();
            Cash c = checkCash(cash, i);
            if (c != null)
                change.add(c);
        }
        return change;
    }

    private Cash checkCash(Cash cash, Integer nr) {
        if (cash.getCount() >= nr) {
            for (int i = 0; i < nr; i++) {
                reduce(cash.getValue());
                cashService.returnCash(cash.getValue());
            }
            return Cash.builder().value(cash.getValue()).name(cash.getName()).count(nr).build();
        } else {
            if (nr > 0)
                checkCash(cash, nr - 1);
        }
        return null;
    }
}
