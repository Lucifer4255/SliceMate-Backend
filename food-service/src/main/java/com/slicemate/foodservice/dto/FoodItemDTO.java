package com.slicemate.foodservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodItemDTO {
    private Long foodItemId;

    @NotBlank(message="Name is required")
    private String name;

    @NotBlank(message="Description is required")
    private String description;
    private String imageUrl;
    @NotBlank(message="Price is required")
    private Double price;

    @NotBlank(message="Category is required")
    private String category;
}
