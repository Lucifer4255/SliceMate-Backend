package com.example.slicemate.controller;

import com.example.slicemate.entity.OrderItem;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderItemController {
    public List<OrderItem> getAllOrders(@PathVariable String id);
}
