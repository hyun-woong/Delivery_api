package com.sparta.delivery_api.Repository;


import com.sparta.delivery_api.Entity.FoodList;
import com.sparta.delivery_api.Entity.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfo, Long> {
    List<StoreInfo> findAll();
}

