package com.sparta.delivery_api.Service;

import com.sparta.delivery_api.Dto.FoodList.FoodResponseDto;
import com.sparta.delivery_api.Dto.Orders.FoodOrderRequestDto;
import com.sparta.delivery_api.Dto.Orders.OrderRequestDto;
import com.sparta.delivery_api.Entity.OrderDetailed;
import com.sparta.delivery_api.Entity.StoreInfo;
import com.sparta.delivery_api.Repository.OrdersRepository;
import com.sparta.delivery_api.Repository.StoreInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final StoreInfoRepository storeInfoRepository;
    private final FoodService foodService;
    private final OrdersRepository ordersRepository;

    @Transactional
    public void foodOrders(OrderRequestDto orderRequestDto) {
        //음식점 ID
        StoreInfo storeInfo = storeInfoRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("음식점이 존재하지 않습니다.")
        );

        //음식 이름, 수량, 가격이 저장된 친구
        List<OrderDetailed> orderDetaileds = new ArrayList<>();

        //주문 수량 및 음식 ID 확인
        List<FoodOrderRequestDto> foodOrderRequestDtos = orderRequestDto.getFoods();

        for (FoodOrderRequestDto foodOrderRequestDto : foodOrderRequestDtos){
            if (foodOrderRequestDto.getQuantity() > 100 || foodOrderRequestDto.getQuantity() < 1){
                throw new NullPointerException("주문 수량이 실화이십니까?");
            }

            //ID 값에 해당하는 음식점의 메뉴 목록을 가져옴
            List<FoodResponseDto> foodList = foodService.getFoodList(orderRequestDto.getRestaurantId());

            //반복문을 돌면서 음식 ID 값에 맞는 정보를 찾음
            for (FoodResponseDto foodResponseDto : foodList){
                if (!foodResponseDto.getId().equals(foodResponseDto.getId())){
                    continue; // 반복문에서 해당 조건에 걸리면, 해당 반복문을 멈추고 다음 단계로 넘어감
                }

                //반복문을 돌며 조건에 부합한 친구들을 저장
                String foodname = foodResponseDto.getName();
                String storeName = storeInfo.getName();
                int quantity = foodOrderRequestDto.getQuantity();
                int price = quantity * foodResponseDto.getPrice();
                int deliveryFee = storeInfo.getDeliveryFee();
                int totalPrice = price + deliveryFee;
                int minOrderPrice = storeInfo.getMinOrderPrice();

                if (price < minOrderPrice){
                    throw new NullPointerException("최소 주문금액 미달입니다.");
                }

                //여러개 주문 시 하나로 합쳐서 총 금액을 계산해야하는데 그러질 못해서 에러나는 듯
                //DTO 쪼개서 처리해보자!

                //값을 Entity 테이블에 저장해주는 역할
                OrderDetailed orderDetailed = new OrderDetailed(
                      storeName,
                      foodname,
                      quantity,
                      price,
                      deliveryFee,
                      totalPrice,
                      minOrderPrice
                );



                orderDetaileds.add(orderDetailed);
                ordersRepository.save(orderDetailed);

                System.out.println("가게명 : " + storeName);
                System.out.println("음식명 : " + foodname);
                System.out.println("수량 : " + quantity);
                System.out.println("가격 : " + price);
                System.out.println("배달비 : " + deliveryFee);
                System.out.println("최소 주문금액 : " + minOrderPrice);
                System.out.println("총 결제금액 : " + totalPrice);
            }

        }




    }
}
