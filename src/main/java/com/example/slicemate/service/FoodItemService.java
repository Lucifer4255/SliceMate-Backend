package com.example.slicemate.service;

import com.example.slicemate.entity.FoodItem;

import java.util.List;

public interface FoodItemService {

    public void addFoodItem(FoodItem foodItem);

    public List<FoodItem> getAllFoodItems();

    public void updateFoodItems(FoodItem foodItem, Integer id);

    public void deleteFoodItem(Integer id);
}
