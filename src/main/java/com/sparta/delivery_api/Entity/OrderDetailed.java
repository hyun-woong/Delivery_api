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
    private Long DetailedId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;


    public OrderDetailed(String name, int price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
