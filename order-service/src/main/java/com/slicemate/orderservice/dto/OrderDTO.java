package com.slicemate.orderservice.dto;

import com.slicemate.orderservice.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long userId;
    private List<OrderItemDTO> orderItems;
    private Double totalPrice;
    private Date orderDate;
    private OrderStatus status;
}
