package com.example.slicemate.payloads;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodItemDto {
    @NotNull
    private Integer FoodItemId;
    @NotEmpty
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String imageUrl;
    @NotNull
    private String Category;
    @NotNull
    private Double price;
    @JsonBackReference
    private CartItem cartItem;
    @JsonBackReference
    private OrderItem orderItem;
}
