package com.slicemate.foodservice.controller;

import com.slicemate.foodservice.dto.FoodItemDTO;
import com.slicemate.foodservice.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping()
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems(){
        return ResponseEntity.ok(foodItemService.getallFoodItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDTO> getFoodItemById(@PathVariable Long id){
        return foodItemService.getFoodItemById(id) == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(foodItemService.getFoodItemById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<FoodItemDTO>> getFoodItemsByCategory(@PathVariable String category){
        return ResponseEntity.ok(foodItemService.getFoodItemsByCategory(category));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO,@RequestParam MultipartFile file){
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemService.addFoodItem(foodItemDTO,file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id){
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.ok("Food Item Deleted Successfully");
    }

    @PutMapping()
    public ResponseEntity<FoodItemDTO> updateFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        return ResponseEntity.ok(foodItemService.updateFoodItem(foodItemDTO));
    }

    @GetMapping("/exists/{id}")
    public Boolean exists(@PathVariable Long id){
        return foodItemService.isFoodItemExist(id);
    }
}
