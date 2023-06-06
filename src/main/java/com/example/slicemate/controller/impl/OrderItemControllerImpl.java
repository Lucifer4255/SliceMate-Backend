package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.OrderItemController;
import com.example.slicemate.payloads.OrderItemDto;
import com.example.slicemate.service.OrderItemService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<OrderItemDto>> getAllOrders(@PathVariable Integer id) {
        return ResponseEntity.ok(this.orderItemService.getAllOrderItems(id));
    }

    @PostMapping("/addOrder/user/{userId}/food/{foodId}")
    public ResponseEntity<OrderItemDto> addOrder(@RequestBody @Valid OrderItemDto orderItemDto, @PathVariable Integer userId, @PathVariable Integer foodId){
        OrderItemDto createdItem=this.orderItemService.saveItem(orderItemDto,userId,foodId);
        return new ResponseEntity<>(createdItem,HttpStatus.CREATED);
    }
}
