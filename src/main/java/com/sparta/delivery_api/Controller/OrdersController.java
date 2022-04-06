package com.sparta.delivery_api.Controller;

import com.sparta.delivery_api.Dto.Orders.OrderDto;
import com.sparta.delivery_api.Dto.Orders.OrderRequestDto;
import com.sparta.delivery_api.Service.OrdersService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/order/request")
    public OrderDto foodOrders(@RequestBody OrderRequestDto orderRequestDto){
       return ordersService.foodOrders(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders(){
        return ordersService.getOrders();
    }



}
