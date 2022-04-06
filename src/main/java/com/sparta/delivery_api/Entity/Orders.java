package com.sparta.delivery_api.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Orders { //음식 주문 테이블

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String storeName;

    private int totalPrice;

    private int deliveryFee;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "OrderDetailed_Id")
    private List<OrderDetailed> orderDetailed;

    public Orders(String storeName, List<OrderDetailed> orderDetailed, int deliveryFee, int totalPrice){
        this.storeName = storeName;
        this.orderDetailed = orderDetailed;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;

    }



}
