package com.sparta.delivery_api.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orders { //음식 주문 테이블


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long OrdersId;





}
