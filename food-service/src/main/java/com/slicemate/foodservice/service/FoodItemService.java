package com.slicemate.foodservice.service;

import com.slicemate.foodservice.dto.FoodItemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodItemService {
    public List<FoodItemDTO> getallFoodItems();
    public FoodItemDTO getFoodItemById(Long id);
    public List<FoodItemDTO> getFoodItemsByCategory(String category);
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO, MultipartFile file);
    public void deleteFoodItem(Long id);
    public FoodItemDTO updateFoodItem(FoodItemDTO foodItemDTO);
    public Boolean isFoodItemExist(Long id);
}
