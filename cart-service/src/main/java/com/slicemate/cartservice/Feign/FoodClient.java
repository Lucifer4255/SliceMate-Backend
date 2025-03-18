package com.slicemate.cartservice.Feign;

import com.slicemate.cartservice.dto.FoodItemDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "food-service", url = "http://localhost:8082/food")
public interface FoodClient {
    @GetMapping("/exists/{id}")
    @CircuitBreaker(name = "foodService",fallbackMethod = "getFoodItemFallback")
    @Retry(name = "foodService",fallbackMethod = "getFoodItemFallback")
    public Boolean exists(@PathVariable Long id);

    @GetMapping("/{id}")
    @CircuitBreaker(name = "foodService",fallbackMethod = "getFoodItemFallback")
    @Retry(name = "foodService",fallbackMethod = "getFoodItemFallback")
    public ResponseEntity<FoodItemDTO> getFoodItemById(@PathVariable Long id);

    default ResponseEntity<FoodItemDTO> getFoodItemFallback(Long id,Throwable throwable) {
        return ResponseEntity.internalServerError().body(new FoodItemDTO(null, "Unknown", "N/A", "https://example.com/default-image.png", 0.0, "N/A"));
    }
}
