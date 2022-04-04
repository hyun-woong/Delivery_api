package com.sparta.delivery_api.Controller;

import com.sparta.delivery_api.Dto.FoodListDto;
import com.sparta.delivery_api.Dto.FoodResponseDto;
import com.sparta.delivery_api.Entity.FoodList;
import com.sparta.delivery_api.Service.FoodService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void foodList(@PathVariable Long restaurantId, @RequestBody @Valid List<FoodListDto> foodListDto){
        foodService.foodList(restaurantId, foodListDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> getFoodList(@PathVariable Long restaurantId){
        return foodService.getFoodList(restaurantId);
    }
}
