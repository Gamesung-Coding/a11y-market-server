package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public record OrderCreateRequest(@NotNull String addressId,
                                 List<String> cartItemIds,
                                 OrderRequestItem directOrderItem) {

    public boolean isFromCart() {
        return cartItemIds != null && !cartItemIds.isEmpty();
    }
}
