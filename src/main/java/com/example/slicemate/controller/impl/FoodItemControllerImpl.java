package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.FoodItemController;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodItemControllerImpl implements FoodItemController {
    @Autowired
    private FoodItemService foodItemService;
    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }
    //Get All food Items
    @GetMapping("/getItems")
    public List<FoodItem> getFood(){
        return foodItemService.getAllFoodItems();
    }
    //update food items
    @PutMapping("/updateItems/{id}")
    public void updateFood(FoodItem foodItem, Integer id) {
        foodItemService.updateFoodItems(foodItem,id);
    }

    //Add Food item
    @PostMapping("/addItems")
    public void addFood(@RequestBody FoodItem foodItem){
        foodItemService.addFoodItem(foodItem);
    }
    //deleting food item by id
    @DeleteMapping("/deleteItems/{id}")
    public void deleteFood(@PathVariable Integer id){
        foodItemService.deleteFoodItem(id);
    }


}
