package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.order.entity.Orders;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        String userName,
        String userEmail,
        String userPhone,
        String receiverName,
        String receiverPhone,
        String receiverZipcode,
        String receiverAddr1,
        String receiverAddr2,
        Integer totalPrice,
        String orderStatus,
        LocalDateTime createdAt) {

    public static OrderResponse fromEntity(Orders order) {
        String status = switch (order.getOrderStatus()) {
            case PENDING -> "결제 대기";
            case PAID -> "결제 완료";
            case ACCEPTED -> "주문 완료";
            case CANCELLED -> "주문 취소";
            case REJECTED -> "주문 거절";
            case SHIPPED -> "배송 중";
            case DELIVERED -> "배송 완료";
        };

        return new OrderResponse(
                order.getOrderId(),
                order.getUserName(),
                order.getUserEmail(),
                order.getUserPhone(),
                order.getReceiverName(),
                order.getReceiverPhone(),
                order.getReceiverZipcode(),
                order.getReceiverAddr1(),
                order.getReceiverAddr2(),
                order.getTotalPrice(),
                status,
                order.getCreatedAt()
        );
    }
}
