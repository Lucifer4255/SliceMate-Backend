package com.slicemate.paymentservice.dto;

import com.slicemate.paymentservice.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private Long orderId;
    private Double amount;
    private PaymentStatus paymentStatus;
}
