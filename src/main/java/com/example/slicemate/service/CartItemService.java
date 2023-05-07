package com.example.slicemate.service;

import com.example.slicemate.entity.CartItem;

import java.util.List;

public interface CartItemService {

    public List<CartItem> showCart(String id);
    public void addCartItem(CartItem cartItem);

    public void deletecartItem(Integer id);

    public void deleteAllItems(String id);
}
