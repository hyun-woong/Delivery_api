package com.sparta.delivery_api.Entity;

import com.sparta.delivery_api.Dto.FoodList.FoodListDto;
import com.sparta.delivery_api.Dto.Orders.FoodOrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FoodList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long foodId;

    @ManyToOne
    @JoinColumn(name = "Store_ID", nullable = false)
    private StoreInfo storeInfo;

    @Column(nullable = false) // 메뉴이름
    private String name;

    @Column(nullable = false) // 가격
    private int price;


    //가게에 음식 메뉴 추가하기
    public FoodList(FoodListDto foodListDto, StoreInfo storeInfo){
        this.name = foodListDto.getName();
        this.price = foodListDto.getPrice();
        this.storeInfo = storeInfo;
    }
}

