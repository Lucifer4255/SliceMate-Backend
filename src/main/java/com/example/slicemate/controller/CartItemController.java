package com.example.slicemate.controller;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.payloads.ApiResponse;
import com.example.slicemate.payloads.CartItemDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CartItemController {
    public ResponseEntity<List<CartItemDto>> showCartItems(@PathVariable String id);
    public ResponseEntity<CartItemDto> addToCart(@RequestBody CartItemDto cartItemDto);
    public ResponseEntity<ApiResponse> deleteItem(@PathVariable Integer id);
    public ResponseEntity<ApiResponse> emptyCart(@PathVariable String id);
}
