package com.slicemate.cartservice.service;

import com.slicemate.cartservice.dto.CartItemDTO;

import java.util.List;

public interface CartItemService {
    List<CartItemDTO> getCartByUserId(Long userId);
    CartItemDTO addtoCart(CartItemDTO cartItemDTO);
    void removeFromCart(Long cartItemId);
    void clearCart(Long userId);
    public Double getTotalPrice(Long userId);
}
