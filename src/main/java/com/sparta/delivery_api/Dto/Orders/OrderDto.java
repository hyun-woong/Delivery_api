package com.sparta.delivery_api.Dto.Orders;

import com.sparta.delivery_api.Entity.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderDto(Orders orders, List<FoodOrderDto> foodOrderDtos) {
        this.restaurantName = orders.getStoreName();
        this.foods = foodOrderDtos;
        this.deliveryFee = orders.getDeliveryFee();
        this.totalPrice = orders.getTotalPrice();
    }
}
