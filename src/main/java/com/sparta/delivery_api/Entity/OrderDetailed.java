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
    private String storeName;

    @Column
    private int price;

    @Column
    private int quantity;

    @Column
    private String foodname;

    @Column
    private int deliveryFee;

    @Column
    private int totalPrice;

    @Column
    private int minOrderPrice;

    public OrderDetailed(String storeName, String name, int price, int quantity, int deliveryFee, int totalPrice, int minOrderPrice){
        this.storeName = storeName;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.minOrderPrice = minOrderPrice;
    }

}
