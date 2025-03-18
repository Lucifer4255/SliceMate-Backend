package com.slicemate.orderservice.clients;

import com.slicemate.orderservice.dto.PaymentDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.ws.rs.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service" , url = "http://localhost:8085/payments/")
public interface PaymentServiceClient {
    @PostMapping("/process")
    @CircuitBreaker(name = "paymentService" , fallbackMethod = "paymentFallback")
    @Retry(name = "paymentService" , fallbackMethod = "paymentFallback")
    public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentDTO paymentDTO);

    default ResponseEntity<PaymentDTO> paymentFallback(Long id, Throwable throwable) {
        return ResponseEntity.badRequest().body(new PaymentDTO(null,0.0,"CANCELLED"));
    }
}