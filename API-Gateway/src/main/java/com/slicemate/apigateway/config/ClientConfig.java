package com.slicemate.apigateway.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8080/auth")
public interface ClientConfig {
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token);
}
