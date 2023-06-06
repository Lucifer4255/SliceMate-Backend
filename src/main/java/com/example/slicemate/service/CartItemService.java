package com.example.slicemate.service;

import com.example.slicemate.payloads.CartItemDto;

import java.util.List;

public interface CartItemService {

    public List<CartItemDto> showCart(Integer id);
    public CartItemDto addCartItem(CartItemDto cartItemDto, Integer userId, Integer foodId);

    public void deletecartItem(Integer id);

    public void deleteAllItems(Integer id);
}
