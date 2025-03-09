package com.slicemate.orderservice.mapper;

import com.slicemate.orderservice.dto.CartItemDTO;
import com.slicemate.orderservice.dto.OrderDTO;
import com.slicemate.orderservice.dto.OrderItemDTO;
import com.slicemate.orderservice.entity.Order;
import com.slicemate.orderservice.entity.OrderItem;
import lombok.Builder;

@Builder
public class OrderMapper {
    public static OrderItemDTO cartToOrderItemDTO(CartItemDTO cartItemDTO){
        return OrderItemDTO.builder()
                .foodItemId(cartItemDTO.getFoodItemId())
                .quantity(cartItemDTO.getQuantity())
                .Price(cartItemDTO.getPrice())
                .build();
    }
    public static OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem){
        return OrderItemDTO.builder()
                .foodItemId(orderItem.getFoodItemId())
                .quantity(orderItem.getQuantity())
                .Price(orderItem.getPrice())
                .build();
    }

    public static OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO){
        return OrderItem.builder()
                .foodItemId(orderItemDTO.getFoodItemId())
                .quantity(orderItemDTO.getQuantity())
                .Price(orderItemDTO.getPrice())
                .build();
    }
}
