package com.example.slicemate.servive.impl;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.repository.CartItemRepository;
import com.example.slicemate.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> showCart(String id) {
        List<CartItem> items =new ArrayList<>();
        cartItemRepository.findByUser(id).forEach(item->items.add(item));
        return items;
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deletecartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void deleteAllItems(String id) {
        cartItemRepository.deleteAllByUser(id);
    }
}
