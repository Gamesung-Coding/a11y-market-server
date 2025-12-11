package com.multicampus.gamesungcoding.a11ymarketserver.feature.order.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.cart.dto.CartItemDto;

import java.util.List;

public record OrderSheetResponse(List<CartItemDto> items,
                                 Integer totalAmount,
                                 Integer shippingFee,
                                 Integer finalAmount) {
}
