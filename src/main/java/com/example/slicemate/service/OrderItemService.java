package com.example.slicemate.service;

import com.example.slicemate.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    public List<OrderItem> getAllOrderItems(String id);

    public void saveItem(OrderItem orderItem);
}
