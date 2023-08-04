package com.example.slicemate.controller;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.ApiResponse;
import com.example.slicemate.payloads.FoodItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface FoodItemController {
    public String helloWorld();
    public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody FoodItemDto foodItemDto);
    public ResponseEntity<FoodItemDto> getFoodbyId(@PathVariable Integer id);
    public ResponseEntity<List<FoodItemDto>> searchFoodbyName(@PathVariable String name);
    public ResponseEntity<List<FoodItemDto>> getFood(@RequestParam(value="pageNumber",defaultValue = "1",required = false) Integer pageNumber,
                                                     @RequestParam(value="pageSize",defaultValue = "6",required = false)Integer pageSize);
    public ResponseEntity<FoodItemDto> updateFood(@RequestBody FoodItemDto foodItemDto, @PathVariable Integer id);
    public ResponseEntity<ApiResponse> deleteFood(@PathVariable Integer id);
}
