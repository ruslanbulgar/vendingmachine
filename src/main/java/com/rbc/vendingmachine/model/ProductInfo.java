package com.rbc.vendingmachine.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products_info")
public class ProductInfo {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "5")
            }
    )
    private Integer code;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "code", orphanRemoval = true)
    private List<Product> products;
    private Integer price;
    private Integer maxProducts;

    @Builder
    public ProductInfo(String name, Integer price, Integer maxProducts) {
        this.name = name;
        this.price = price;
        this.maxProducts = maxProducts;
    }
}
