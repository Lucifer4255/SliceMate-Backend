package com.example.slicemate.controller;

import com.example.slicemate.payloads.ApiResponse;
import com.example.slicemate.payloads.CartItemDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CartItemController {
    public ResponseEntity<List<CartItemDto>> showCartItems(@PathVariable Integer id);
    public ResponseEntity<CartItemDto> addToCart(@RequestBody CartItemDto cartItemDto,@PathVariable Integer userId,@PathVariable Integer foodId);
    public ResponseEntity<CartItemDto> updateCartItem(@RequestBody CartItemDto cartItemDto,@PathVariable Integer id);
    public ResponseEntity<ApiResponse> deleteItem(@PathVariable Integer id);
    public ResponseEntity<ApiResponse> emptyCart(@PathVariable Integer id);
}
