package com.example.slicemate.controller;

import com.example.slicemate.entity.FoodItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface FoodItemController {
    public String helloWorld();
    public void addFood(@RequestBody FoodItem foodItem);
    public List<FoodItem> getFood();
    public void updateFood(@RequestBody FoodItem foodItem, @PathVariable Integer id);
    public void deleteFood(@PathVariable Integer id);
}
