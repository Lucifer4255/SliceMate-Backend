package com.slicemate.paymentservice.service;

import com.slicemate.paymentservice.dto.PaymentDTO;
import com.slicemate.paymentservice.entity.Payment;
import com.slicemate.paymentservice.entity.PaymentStatus;
import com.slicemate.paymentservice.repository.PaymentRepository;
import lombok.Builder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Builder
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "payment_queue")
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        Payment payment = Payment.builder()
                .orderId(paymentDTO.getOrderId())
                .amount(paymentDTO.getAmount())
                .paymentStatus(mockPayment())
                .build();
        Payment saved = paymentRepository.save(payment);


        PaymentDTO responseDTO= PaymentDTO.builder()
                .orderId(saved.getOrderId())
                .amount(saved.getAmount())
                .paymentStatus(saved.getPaymentStatus())
                .build();

        rabbitTemplate.convertAndSend("order_update_queue", responseDTO);
        return responseDTO;
    }

    public PaymentStatus mockPayment() {
        return new Random().nextBoolean() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }
}
