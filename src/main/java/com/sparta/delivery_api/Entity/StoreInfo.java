package com.sparta.delivery_api.Entity;

import com.sparta.delivery_api.Dto.StoreInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@Entity // DB테이블과 매핑시켜줌
public class StoreInfo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    public StoreInfo(StoreInfoDto storeInfoDto){
        this.name = storeInfoDto.getName();
        this.minOrderPrice = storeInfoDto.getMinOrderPrice();
        this.deliveryFee = storeInfoDto.getDeliveryFee();
    }


}

