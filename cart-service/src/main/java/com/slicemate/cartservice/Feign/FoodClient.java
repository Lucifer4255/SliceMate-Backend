package com.slicemate.cartservice.Feign;

import com.slicemate.cartservice.dto.FoodItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "food-service", url = "http://localhost:8082/food")
public interface FoodClient {
    @GetMapping("/exists/{id}")
    public Boolean exists(@PathVariable Long id);

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDTO> getFoodItemById(@PathVariable Long id);
}
