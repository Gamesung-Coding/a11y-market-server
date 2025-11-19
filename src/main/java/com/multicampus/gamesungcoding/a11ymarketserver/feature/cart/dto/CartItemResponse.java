package com.multicampus.gamesungcoding.a11ymarketserver.feature.cart.dto;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.cart.entity.CartItems;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CartItemResponse(
        @NotBlank(message = "cart item id is required.")
        UUID cartItemId,

        @NotBlank(message = "cart id is required.")
        UUID cartId,

        @NotBlank(message = "product id is required.")
        UUID productId,

        @NotBlank(message = "quantity is required.")
        @Min(message = "quantity must be at least 1.", value = 1)
        Integer quantity) {

    public static CartItemResponse fromEntity(CartItems cartItems) {
        return new CartItemResponse(
                cartItems.getCartItemId(),
                cartItems.getCartId(),
                cartItems.getProductId(),
                cartItems.getQuantity());
    }
}