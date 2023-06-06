package com.example.slicemate.service;

import com.example.slicemate.payloads.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    public List<OrderItemDto> getAllOrderItems(Integer id);

    public OrderItemDto saveItem(OrderItemDto orderItemDto, Integer foodId, Integer userId);
}
