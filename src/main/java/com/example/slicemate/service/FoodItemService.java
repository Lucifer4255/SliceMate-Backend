package com.example.slicemate.service;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.FoodItemDto;

import java.util.List;

public interface FoodItemService {

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto);

    public List<FoodItemDto> getAllFoodItems();

    public FoodItemDto updateFoodItems(FoodItemDto foodItemDto, Integer id);

    public void deleteFoodItem(Integer id);
}