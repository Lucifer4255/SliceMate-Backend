package com.slicemate.cartservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081/users")
public interface UserClient {
    @GetMapping("/exists/{id}")
    public Boolean exists(@PathVariable Long id);
}
