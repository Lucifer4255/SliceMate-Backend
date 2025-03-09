package com.slicemate.orderservice.clients;

import com.slicemate.orderservice.dto.CartItemDTO;
import com.slicemate.orderservice.dto.OrderItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cart-service", url = "http://localhost:8084/cart")
public interface CartServiceClient {
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartByUserId(@PathVariable Long userId);
}
