package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCheckRequest(
        List<String> checkoutItemIds,
        @NotNull Boolean orderAllItems) {
}
