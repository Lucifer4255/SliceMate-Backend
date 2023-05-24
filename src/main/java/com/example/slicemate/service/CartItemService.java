package com.example.slicemate.service;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.payloads.CartItemDto;

import java.util.List;

public interface CartItemService {

    public List<CartItemDto> showCart(String id);
    public CartItemDto addCartItem(CartItemDto cartItemDto);

    public void deletecartItem(Integer id);

    public void deleteAllItems(String id);
}
