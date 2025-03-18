package com.slicemate.orderservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081/auth")
public interface UserServiceClient {
    @GetMapping("/exists/{id}")
    public Boolean existsByUserId(@PathVariable Long id);
}
