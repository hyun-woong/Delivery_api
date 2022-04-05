package com.sparta.delivery_api.Entity;


import com.sparta.delivery_api.Dto.FoodOrdersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FoodOrders { //음식 주문 테이블


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long foodOrdersId;

    @ManyToOne
    @JoinColumn(name = "StoreId", nullable = false)
    private StoreInfo storeInfo;

    @ManyToOne
    @JoinColumn
    private FoodList foodList;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int totalFoodPrice;


    public FoodOrders(FoodOrdersDto foodOrdersDto, StoreInfo storeInfo, FoodList foodList){
        this.quantity = foodOrdersDto.getQuantity();
        this.totalFoodPrice = foodOrdersDto.getTotalFoodPrice();
        this.storeInfo = storeInfo;
        this.foodList = foodList;
    }



}
