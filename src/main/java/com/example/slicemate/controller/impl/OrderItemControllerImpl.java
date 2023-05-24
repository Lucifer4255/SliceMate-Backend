package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.OrderItemController;
import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.payloads.OrderItemDto;
import com.example.slicemate.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class OrderItemControllerImpl implements OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrderItemDto>> getAllOrders(@PathVariable String id) {
        return ResponseEntity.ok(this.orderItemService.getAllOrderItems(id));
    }

    @PostMapping("/addOrder")
    public ResponseEntity<OrderItemDto> addOrder(@RequestBody OrderItemDto orderItemDto){
        OrderItemDto createdItem=this.orderItemService.saveItem(orderItemDto);
        return new ResponseEntity<>(createdItem,HttpStatus.CREATED);
    }
}
