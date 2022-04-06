package com.sparta.delivery_api.Service;

import com.sparta.delivery_api.Dto.FoodList.FoodListDto;
import com.sparta.delivery_api.Dto.FoodList.FoodResponseDto;
import com.sparta.delivery_api.Dto.Orders.FoodOrderDto;
import com.sparta.delivery_api.Dto.Orders.FoodOrderRequestDto;
import com.sparta.delivery_api.Dto.Orders.OrderRequestDto;
import com.sparta.delivery_api.Entity.FoodList;
import com.sparta.delivery_api.Entity.OrderDetailed;
import com.sparta.delivery_api.Entity.StoreInfo;
import com.sparta.delivery_api.Repository.FoodRepository;
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
    private final FoodRepository foodRepository;
    private final FoodService foodService;
    private final OrdersRepository ordersRepository;

    @Transactional
    public void foodOrders(OrderRequestDto orderRequestDto) {
        //음식점 ID
        StoreInfo storeInfo = storeInfoRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("음식점이 존재하지 않습니다.")
        );
        FoodList foodList = foodRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("메뉴를 찾을 수 없습니다.")
        );


        //음식 이름, 수량, 가격이 저장된 친구
        List<OrderDetailed> orderDetaileds = new ArrayList<>();
        int sumPrice = 0;

        //주문 수량 및 음식 ID 확인
        List<FoodOrderRequestDto> foodID_quantity = orderRequestDto.getFoods();

        //주문 수량 및 음식 ID 최종 리스트 값
        List<FoodOrderRequestDto> foodOrderRequestDtos = new ArrayList<>();

        //음식수량 필터링 및 메뉴ID, 수량 Get
        for (FoodOrderRequestDto foodOrderRequestDto : foodID_quantity){
            if (foodOrderRequestDto.getQuantity() > 100 || foodOrderRequestDto.getQuantity() < 1){
                throw new NullPointerException("주문 수량이 실화이십니까?");
            }
            Long foodId = foodOrderRequestDto.getId();
            int quantity = foodOrderRequestDto.getQuantity();

            FoodOrderRequestDto foodOrderRequestDto1 = new FoodOrderRequestDto(foodId, quantity);
            foodOrderRequestDtos.add(foodOrderRequestDto1);

            System.out.println("음식아이디 : " + foodId);
            System.out.println("수량 : " + quantity);

            List<FoodResponseDto> foodResponseDtos = foodService.getFoodList(orderRequestDto.getRestaurantId());
            for (FoodResponseDto foodlist : foodResponseDtos){
                if (!foodlist.getId().equals(foodId)){
                    continue;
                }else {
                    String foodName = foodlist.getName();
                    int foodprice = foodlist.getPrice() * quantity;
                    FoodOrderDto foodOrderDto = new FoodOrderDto(foodName, foodprice);

                    System.out.println("음식명: " + foodOrderDto.getName());
                    System.out.println("음식가격: " + foodOrderDto.getPrice());
                }
            }

        }




//        List<FoodResponseDto> foodResponseDtos = foodService.getFoodList(orderRequestDto.getRestaurantId());
//        for (FoodResponseDto foodResponseDto : foodResponseDtos){
//            if (!foodResponseDto.getId().equals(orderRequestDto.getRestaurantId())){
//                continue;
//            }
//        }







//        int deliveryFee = storeInfo.getDeliveryFee();
//        int totalPrice = deliveryFee + sumPrice;
//        System.out.println("총 결제금액 : " + totalPrice);


    }
}
