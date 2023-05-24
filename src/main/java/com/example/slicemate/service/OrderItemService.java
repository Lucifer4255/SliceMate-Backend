package com.example.slicemate.service;

import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.payloads.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    public List<OrderItemDto> getAllOrderItems(String id);

    public OrderItemDto saveItem(OrderItemDto orderItemDto);
}
