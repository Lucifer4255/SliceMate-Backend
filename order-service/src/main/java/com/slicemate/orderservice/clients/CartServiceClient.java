package com.slicemate.orderservice.clients;

import com.slicemate.orderservice.dto.CartItemDTO;
import com.slicemate.orderservice.dto.OrderItemDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "cart-service", url = "http://localhost:8080/cart")
public interface CartServiceClient {
    @CircuitBreaker(name = "cartService",fallbackMethod = "cartFallBack")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartByUserId(@PathVariable Long userId);

    default ResponseEntity<List<CartItemDTO>> cartFallBack(Long userId,Throwable throwable) {
        return ResponseEntity.internalServerError().body(new ArrayList<>());
    }
}
