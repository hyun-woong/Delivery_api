package com.sparta.delivery_api.Repository;

import com.sparta.delivery_api.Entity.OrderDetailed;
import com.sparta.delivery_api.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderDetailed, Long> {

}
