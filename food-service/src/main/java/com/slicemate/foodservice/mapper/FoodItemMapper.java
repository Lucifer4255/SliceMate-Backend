package com.slicemate.foodservice.mapper;

import com.slicemate.foodservice.dto.FoodItemDTO;
import com.slicemate.foodservice.entity.FoodItem;
import lombok.Builder;

@Builder
public class FoodItemMapper {
    public static FoodItemDTO toFoodItemDTO(FoodItem foodItem) {
        return FoodItemDTO.builder()
                .foodItemId(foodItem.getFoodItemId())
                .name(foodItem.getName())
                .description(foodItem.getDescription())
                .imageUrl(foodItem.getImageUrl())
                .price(foodItem.getPrice())
                .category(foodItem.getCategory())
                .build();
    }
    public static FoodItem toFoodItem(FoodItemDTO foodItemDTO) {
        return FoodItem.builder()
                .foodItemId(foodItemDTO.getFoodItemId())
                .name(foodItemDTO.getName())
                .description(foodItemDTO.getDescription())
                .imageUrl(foodItemDTO.getImageUrl())
                .price(foodItemDTO.getPrice())
                .category(foodItemDTO.getCategory())
                .build();
    }
}
