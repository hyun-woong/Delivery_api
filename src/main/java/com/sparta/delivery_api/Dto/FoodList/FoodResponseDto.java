package com.sparta.delivery_api.Dto.FoodList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FoodResponseDto {

    private Long id;
    private int price;
    private String name;

}
