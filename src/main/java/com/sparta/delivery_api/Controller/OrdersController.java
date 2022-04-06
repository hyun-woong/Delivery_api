package com.sparta.delivery_api.Controller;

import com.sparta.delivery_api.Dto.Orders.OrderDto;
import com.sparta.delivery_api.Dto.Orders.OrderRequestDto;
import com.sparta.delivery_api.Entity.Orders;
import com.sparta.delivery_api.Service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/order/request")
    public OrderDto foodOrders(@RequestBody OrderRequestDto orderRequestDto){
       return ordersService.foodOrders(orderRequestDto);
    }



}
