package com.rbc.vendingmachine.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer code;
    private LocalDate expirationDate;

    @Builder
    public Product(Integer code, LocalDate expirationDate) {
        this.code = code;
        this.expirationDate = expirationDate;
    }
}
