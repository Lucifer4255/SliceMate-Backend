package com.example.slicemate.service;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.FoodItemDto;

import java.util.List;

public interface FoodItemService {

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto);

    public List<FoodItemDto> getAllFoodItems(Integer pageNumber,Integer pageSize);

    public FoodItemDto updateFoodItems(FoodItemDto foodItemDto, Integer id);

    public void deleteFoodItem(Integer id);

	public FoodItemDto getFoodItem(Integer id);

	public List<FoodItemDto> searchByName(String name);
}
