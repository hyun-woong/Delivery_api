package com.sparta.delivery_api.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orders {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long ordersId;

    @ManyToOne
    @JoinColumn
    private FoodOrders foodOrders;

    @Column(nullable = false)
    private int totalPrice;
    //배달비 + 음식가격






}
