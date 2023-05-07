package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.OrderItemController;
import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class OrderItemControllerImpl implements OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/orders/{id}")
    public List<OrderItem> getAllOrders(@PathVariable String id) {
        return orderItemService.getAllOrderItems(id);
    }

    @PostMapping("/addOrder")
    public void addOrder(@RequestBody OrderItem orderItem){
        orderItemService.saveItem(orderItem);

    }
}
