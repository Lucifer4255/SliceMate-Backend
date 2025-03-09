package com.slicemate.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemDTO {
    private Long foodItemId;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private String category;
}
