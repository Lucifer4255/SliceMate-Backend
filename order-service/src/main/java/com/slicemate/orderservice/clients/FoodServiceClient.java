package com.slicemate.orderservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "food-service", url = "http://localhost:8082/food")
public interface FoodServiceClient {
    @GetMapping("/exists/{id}")
    public Boolean existsByFoodId(@PathVariable Long id);
}
