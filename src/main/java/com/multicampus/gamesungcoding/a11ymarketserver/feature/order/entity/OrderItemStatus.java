package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.entity;

import java.util.List;

public enum OrderItemStatus {
    ORDERED,
    PAID,
    SHIPPED,
    CONFIRMED,
    CANCEL_PENDING,
    CANCELED,
    RETURN_PENDING,
    RETURNED;

    public static List<OrderItemStatus> inProgressStatuses() {
        return List.of(ORDERED, PAID, SHIPPED, CANCELED, RETURN_PENDING);
    }
}
