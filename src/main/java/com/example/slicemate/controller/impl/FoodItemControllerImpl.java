package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.FoodItemController;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.ApiResponse;
import com.example.slicemate.payloads.FoodItemDto;
import com.example.slicemate.service.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodItems")
public class FoodItemControllerImpl implements FoodItemController {
    @Autowired
    private FoodItemService foodItemService;
    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }
    @GetMapping("/getItem/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<FoodItemDto> getFoodbyId(@PathVariable Integer id){
    	return ResponseEntity.ok(foodItemService.getFoodItem(id));
    }
    //Get All food Items
    @GetMapping("/getItems")
    public ResponseEntity<List<FoodItemDto>> getFood(){
        return ResponseEntity.ok(this.foodItemService.getAllFoodItems());
    }

    //update food items
    @PutMapping("/updateItems/{id}")
    public ResponseEntity<FoodItemDto> updateFood(@RequestBody @Valid FoodItemDto foodItemDto, @PathVariable Integer id) {
        FoodItemDto updatedItemDto = this.foodItemService.updateFoodItems(foodItemDto, id);
        return ResponseEntity.ok(updatedItemDto);
    }
    //Add Food item
    @PostMapping("/addItems")
    public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody @Valid FoodItemDto foodItemDto) {
        FoodItemDto createdItem = this.foodItemService.addFoodItem(foodItemDto);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }
    //deleting food item by id
    @DeleteMapping("/deleteItems/{id}")
    public ResponseEntity<ApiResponse> deleteFood(@PathVariable Integer id){
        this.foodItemService.deleteFoodItem(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Item deleted successfully",true),HttpStatus.OK);
    }

	@GetMapping("/searchFood")
	public ResponseEntity<List<FoodItemDto>> searchFoodbyName(@RequestParam("name") String name) {
		return ResponseEntity.ok(this.foodItemService.searchByName(name));
	}


}
