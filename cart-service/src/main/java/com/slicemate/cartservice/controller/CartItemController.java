package com.slicemate.cartservice.controller;

import com.slicemate.cartservice.dto.CartItemDTO;
import com.slicemate.cartservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cartItemService.getCartByUserId(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartItemDTO> addToCart(@RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartItemService.addtoCart(cartItemDTO));
    }
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
        return ResponseEntity.ok("deleted from cart");
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartItemService.clearCart(userId);
        return ResponseEntity.ok("cart cleared");
    }
    @GetMapping("/total-price/{userId}")
    public ResponseEntity<Double> getTotalPrice(@PathVariable Long userId) {
        return ResponseEntity.ok(cartItemService.getTotalPrice(userId));
    }
}
