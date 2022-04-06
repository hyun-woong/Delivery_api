package com.sparta.delivery_api.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderDetailed {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long OrderDetailedId;

    @OneToOne
    @JoinColumn(name = "foodId")
    private FoodList foodList;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private int quantity;


    public OrderDetailed(String name, int price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
