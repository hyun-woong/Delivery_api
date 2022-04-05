package com.sparta.delivery_api.Dto.Orders;

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
}
