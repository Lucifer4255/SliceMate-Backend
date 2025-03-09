package com.slicemate.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {
    private Long cartItemId;
    private Long foodItemId;
    private Long userId;
    private Integer quantity;
    private Double Price;
}
