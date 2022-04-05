package com.sparta.delivery_api.Dto.Orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDto {
    String name;
    int quantity;
    int price;
}
