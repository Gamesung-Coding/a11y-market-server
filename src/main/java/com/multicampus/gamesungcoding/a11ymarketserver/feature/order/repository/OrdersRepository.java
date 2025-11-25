package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.repository;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {

    // 내 주문 목록
    List<Orders> findAllByUserEmailOrderByCreatedAtDesc(String userEmail);

    //내 주문 상세
    Optional<Orders> findByOrderIdAndUserEmail(UUID orderId, String userEmail);
}
