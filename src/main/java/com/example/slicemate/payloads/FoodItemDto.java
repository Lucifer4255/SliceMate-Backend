package com.example.slicemate.payloads;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.OrderItem;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodItemDto {
    private Integer FoodItemId;
    private String name;
    private String description;
    private String imageUrl;
    private String Category;
    private Double price;
    private CartItem cartItem;
    private OrderItem orderItem;
}
