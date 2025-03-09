package com.slicemate.orderservice.service;

import com.slicemate.orderservice.dto.OrderDTO;
import com.slicemate.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    public OrderDTO createOrder(Long userId);
    public List<OrderDTO> getOrdersByUser(Long userId);
}
