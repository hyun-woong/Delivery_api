package com.sparta.delivery_api.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FoodListDto {
    private Long id;
    private String name;
    private int price;
}

