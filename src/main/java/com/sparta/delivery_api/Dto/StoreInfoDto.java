package com.sparta.delivery_api.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StoreInfoDto {

    @NotNull(message = "가게명은 필수 입력사항입니다.")
    private String name;

    @Max(value = 100000)
    @Min(value = 1000)
    private int minOrderPrice;

    @Max(value = 10000)
    @Min(value = 0)
    private int deliveryFee;
}

