package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.dto;

import java.time.LocalDateTime;

public record OrderResponse(
        String orderId,
        String userName,
        String userEmail,
        String userPhone,
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddr1,
        String receiverAddr2,
        Integer totalPrice,
        String OrderStatus,
        LocalDateTime createdAt) {
}
