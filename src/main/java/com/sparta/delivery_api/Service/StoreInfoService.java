package com.sparta.delivery_api.Service;

import com.sparta.delivery_api.Dto.StoreInfoDto;
import com.sparta.delivery_api.Entity.StoreInfo;
import com.sparta.delivery_api.Repository.StoreInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreInfoService {

    private final StoreInfoRepository storeInfoRepository;

    //음식점 POST
    public StoreInfo storeInfo(StoreInfoDto storeInfoDto) {
        StoreInfo storeInfo = new StoreInfo(storeInfoDto);
        int orderprice = storeInfo.getMinOrderPrice();
        int deliveryFee = storeInfo.getDeliveryFee();

        if (deliveryFee % 500 != 0 && deliveryFee != 0){
            throw new NullPointerException("500원 단위로 입력해주세요.");
        }else if (orderprice % 100 != 0){
            throw new NullPointerException("100원 단위로 입력해주세요.");
        }
        return storeInfoRepository.save(storeInfo);
    }

    //음식점 GET
    public List<StoreInfo> getAllStoreInfo() {
        return storeInfoRepository.findAll();

    }
}
