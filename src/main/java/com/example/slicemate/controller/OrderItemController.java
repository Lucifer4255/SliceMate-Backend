package com.example.slicemate.controller;

import com.example.slicemate.payloads.OrderItemDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderItemController {
    public ResponseEntity<List<OrderItemDto>> getAllOrders(@PathVariable Integer id);
    public ResponseEntity<OrderItemDto> addOrder(@RequestBody OrderItemDto orderItemDto,@PathVariable Integer userId,@PathVariable Integer foodId);
}
