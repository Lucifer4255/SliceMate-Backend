package com.example.slicemate.servive.impl;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;
    public void addFoodItem(FoodItem foodItem){
        foodItemRepository.save(foodItem);
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        List<FoodItem> items =(List<FoodItem>) foodItemRepository.findAll();
        return items;
    }

    @Override
    public void updateFoodItems(FoodItem foodItem, Integer id) {
        if(id == foodItem.getFoodItemId()){
            foodItemRepository.save(foodItem);
        }
    }

    @Override
    public void deleteFoodItem(Integer id) {
        foodItemRepository.deleteById(id);
    }

}
