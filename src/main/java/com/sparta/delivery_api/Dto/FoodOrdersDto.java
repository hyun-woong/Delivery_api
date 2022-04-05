package com.sparta.delivery_api.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrdersDto {

    private int quantity;
    private int totalFoodPrice;

}
