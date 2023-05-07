package com.example.slicemate.controller;

import com.example.slicemate.entity.CartItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CartItemController {
    public List<CartItem> showCartItems(@PathVariable String id);
    public void addToCart(@RequestBody CartItem cartItem);
    public void deleteItem(@PathVariable Integer id);

    public void emptyCart(@PathVariable String id);
}
