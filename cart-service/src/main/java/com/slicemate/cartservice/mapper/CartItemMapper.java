package com.slicemate.cartservice.mapper;

import com.slicemate.cartservice.dto.CartItemDTO;
import com.slicemate.cartservice.entity.CartItem;
import lombok.Builder;

@Builder
public class CartItemMapper {
    public static CartItemDTO toCartItemDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                .cartItemId(cartItem.getCartItemId())
                .foodItemId(cartItem.getFoodItemId())
                .userId(cartItem.getUserId())
                .quantity(cartItem.getQuantity())
                .Price(cartItem.getPrice())
                .build();
    }
    public static CartItem toCartItemEntity(CartItemDTO cartItemDTO){
        return CartItem.builder()
                .cartItemId(cartItemDTO.getCartItemId())
                .foodItemId(cartItemDTO.getFoodItemId())
                .userId(cartItemDTO.getUserId())
                .quantity(cartItemDTO.getQuantity())
                .Price(cartItemDTO.getPrice())
                .build();
    }
}
