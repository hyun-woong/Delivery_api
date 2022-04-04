package com.sparta.delivery_api.Service;

import com.sparta.delivery_api.Dto.FoodListDto;
import com.sparta.delivery_api.Dto.FoodResponseDto;
import com.sparta.delivery_api.Entity.FoodList;
import com.sparta.delivery_api.Entity.StoreInfo;
import com.sparta.delivery_api.Repository.FoodRepository;
import com.sparta.delivery_api.Repository.StoreInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final StoreInfoRepository storeInfoRepository;

    @Transactional
    public void foodList(Long restaurantId, List<FoodListDto> foodListDto) {
        StoreInfo storeInfo = storeInfoRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("해당 점포가 존재하지 않습니다.")
        );

        for (FoodListDto foodListDto1 : foodListDto){
           if (foodRepository.existsByNameAndStoreInfo_Id(foodListDto1.getName(), restaurantId)){
               throw new NullPointerException("nono");
           }else if (foodListDto1.getPrice() < 100 || foodListDto1.getPrice() > 1000000){
               throw new NullPointerException("NONO");
           }else if (foodListDto1.getPrice() % 100 != 0){
               throw new NullPointerException("NOOOOOOOO!");
           }
            FoodList foodList = new FoodList(foodListDto1, storeInfo);

            foodRepository.save(foodList);
            }


        }


    @Transactional
    public List<FoodResponseDto> getFoodList(Long restaurantId) {
       StoreInfo storeInfo = storeInfoRepository.findById(restaurantId).orElseThrow(
               () -> new NoSuchElementException("음식점이 없습니다.")
       );
       List<FoodResponseDto> foodLists = new ArrayList<>();
       List<FoodList> getfood = foodRepository.findAllByStoreInfo(storeInfo);

       for (FoodList foodList : getfood){
           FoodResponseDto foodResponseDto = new FoodResponseDto(foodList.getFoodId(), foodList.getPrice(), foodList.getName());
           foodLists.add(foodResponseDto);
       }
       return foodLists;
       }
}

