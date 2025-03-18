package com.slicemate.cartservice.Feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081/users")
public interface UserClient {
    @GetMapping("/exists/{id}")
    @CircuitBreaker(name = "userService" , fallbackMethod = "userFallback")
    @Retry(name = "userService" , fallbackMethod = "userFallback")
    public Boolean exists(@PathVariable Long id);

    default Boolean userFallback(Long id, Throwable throwable) {
        return Boolean.FALSE;
    }
}
