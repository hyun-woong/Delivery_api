package com.sparta.delivery_api.Controller;

import com.sparta.delivery_api.Dto.Orders.OrderRequestDto;
import com.sparta.delivery_api.Entity.OrderDetailed;
import com.sparta.delivery_api.Service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/order/request")
    public void foodOrders(@RequestBody OrderRequestDto orderRequestDto){
       ordersService.foodOrders(orderRequestDto);
    }



}
