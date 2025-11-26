package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.order.entity.OrderItems;

import java.util.UUID;

public record OrderItemResponse(
        UUID orderItemId,
        UUID productId,
        String productName,
        Integer productPrice,
        Integer productQuantity,
        Integer productTotalPrice,
        String productImageUrl,
        String orderItemStatus,
        String cancelReason
) {
    public static OrderItemResponse fromEntity(OrderItems entity) {
        String status = switch (entity.getOrderItemStatus()) {
            case ORDERED -> "주문완료";
            case PAID -> "결제완료";
            case SHIPPED -> "배송중";
            case CONFIRMED -> "배송완료";
            case CANCEL_PENDING -> "취소요청";
            case CANCELED -> "주문취소";
            case RETURN_PENDING -> "반품요청";
            case RETURNED -> "반품완료";
        };

        int totalPrice = entity.getProductPrice() * entity.getProductQuantity();

        return new OrderItemResponse(
                entity.getOrderItemId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getProductQuantity(),
                totalPrice,
                entity.getProductImageUrl(),
                status,
                entity.getCancelReason()
        );
    }
}
