package com.rbc.vendingmachine.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Balance {
    @Id
    public Integer id;
    public Integer balance;

    @Builder
    public Balance(Integer id, Integer balance) {
        this.id = id;
        this.balance = balance;
    }
}
