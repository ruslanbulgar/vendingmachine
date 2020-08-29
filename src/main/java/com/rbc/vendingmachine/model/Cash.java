package com.rbc.vendingmachine.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Cash {

    @Id
    private Integer value;
    private String name;
    private Integer count;

    @Builder
    public Cash(Integer value, String name, Integer count) {
        this.value = value;
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "(" + count + ") " + name + "(s)";
    }
}
