package com.sparta.delivery_api.Dto.Orders;

import com.sparta.delivery_api.Entity.OrderDetailed;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDto {
    String name;
    int quantity;
    int price;

    public FoodOrderDto(OrderDetailed orderDetailed) {
        this.name = orderDetailed.getName();
        this.quantity = orderDetailed.getQuantity();
        this.price = orderDetailed.getPrice();
    }
}
