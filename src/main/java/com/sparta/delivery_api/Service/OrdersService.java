package com.sparta.delivery_api.Service;

import com.sparta.delivery_api.Dto.FoodList.FoodResponseDto;
import com.sparta.delivery_api.Dto.Orders.FoodOrderDto;
import com.sparta.delivery_api.Dto.Orders.FoodOrderRequestDto;
import com.sparta.delivery_api.Dto.Orders.OrderDto;
import com.sparta.delivery_api.Dto.Orders.OrderRequestDto;
import com.sparta.delivery_api.Entity.OrderDetailed;
import com.sparta.delivery_api.Entity.Orders;
import com.sparta.delivery_api.Entity.StoreInfo;
import com.sparta.delivery_api.Repository.OrdersRepository;
import com.sparta.delivery_api.Repository.StoreInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final StoreInfoRepository storeInfoRepository;
    private final FoodService foodService;
    private final OrdersRepository ordersRepository;

    @Transactional
    public OrderDto foodOrders(OrderRequestDto orderRequestDto) {
        //음식점 ID
        StoreInfo storeInfo = storeInfoRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("음식점이 존재하지 않습니다.")
        );
//        FoodList foodList = foodRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
//                () -> new NullPointerException("메뉴를 찾을 수 없습니다.")
//        );


        //음식 이름, 수량, 가격이 저장된 친구
        List<OrderDetailed> orderDetaileds = new ArrayList<>();

        //주문 수량 및 음식 ID 확인
        List<FoodOrderRequestDto> foodID_quantity = orderRequestDto.getFoods();

        List<FoodOrderDto> foodOrderDtos = new ArrayList<>();

//        //주문 수량 및 음식 ID 최종 리스트 값
//        List<FoodOrderRequestDto> foodOrderRequestDtos = new ArrayList<>();

        //음식수량 필터링 및 메뉴ID, 수량 Get
        for (FoodOrderRequestDto foodOrderRequestDto : foodID_quantity) {
            if (foodOrderRequestDto.getQuantity() > 100 || foodOrderRequestDto.getQuantity() < 1) {
                throw new NullPointerException("주문 수량이 실화이십니까?");
            }
            Long foodId = foodOrderRequestDto.getId();

            List<FoodResponseDto> foodResponseDtos = foodService.getFoodList(orderRequestDto.getRestaurantId());
            for (FoodResponseDto foodlist : foodResponseDtos) {
                if (!foodlist.getId().equals(foodId)) {
                    continue;
                }
                String foodName = foodlist.getName();
                int quantity = foodOrderRequestDto.getQuantity();
                int foodprice = foodlist.getPrice() * quantity;


                OrderDetailed orderDetailed = new OrderDetailed(foodName, foodprice, quantity);
                orderDetaileds.add(orderDetailed);
                foodOrderDtos.add(new FoodOrderDto(orderDetailed));

                System.out.println("음식명: " + orderDetailed.getName());
                System.out.println("음식가격: " + orderDetailed.getPrice());
                System.out.println("음식수량: " + orderDetailed.getQuantity());
            }
        }

        int sumFoodPrice = 0;
        for (FoodOrderDto foodInfo : foodOrderDtos) {
            sumFoodPrice += foodInfo.getPrice();
        }

        System.out.println("음식 총 가격: " + sumFoodPrice);

        Orders orders = new Orders(
                storeInfo.getName(),
                orderDetaileds,
                storeInfo.getDeliveryFee(),
                storeInfo.getDeliveryFee() + sumFoodPrice);

        System.out.println("가게이름 : " + orders.getStoreName());
        System.out.println("배달팁 : " + orders.getDeliveryFee());
        System.out.println("총 가격 : " + orders.getTotalPrice());

        if (sumFoodPrice < storeInfo.getMinOrderPrice()) {
            throw new NullPointerException("최소주문 금액 미달입니다.");
        }

        ordersRepository.save(orders);

        return new OrderDto(orders, foodOrderDtos);
    }

    @Transactional
    public List<OrderDto> getOrders() {
        List<Orders> orders = ordersRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Orders orders1 : orders) {
            List<FoodOrderDto> foodOrderDtos = new ArrayList<>();
            for (OrderDetailed orderDetailed : orders1.getOrderDetailed()) {
                FoodOrderDto foodOrderDto = new FoodOrderDto(orderDetailed);
                foodOrderDtos.add(foodOrderDto);
            }
            OrderDto orderDto = new OrderDto(orders1, foodOrderDtos);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}