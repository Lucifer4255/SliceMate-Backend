package com.slicemate.foodservice.service.impl;

import com.slicemate.foodservice.clients.ImageServiceClient;
import com.slicemate.foodservice.dto.FoodItemDTO;
import com.slicemate.foodservice.dto.ImageResponseDTO;
import com.slicemate.foodservice.entity.FoodItem;
import com.slicemate.foodservice.mapper.FoodItemMapper;
import com.slicemate.foodservice.repository.FoodItemRepository;
import com.slicemate.foodservice.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private ImageServiceClient imageServiceClient;

    @Override
    public List<FoodItemDTO> getallFoodItems() {
        return foodItemRepository.findAll().stream().map(FoodItemMapper::toFoodItemDTO).collect(Collectors.toList());
    }

    @Override
    public FoodItemDTO getFoodItemById(Long id) {
        FoodItemDTO foodItemDTO = foodItemRepository.findById(id).map(FoodItemMapper::toFoodItemDTO).orElseThrow(() -> new RuntimeException("Food Item not found"));
        return foodItemDTO;
    }

    @Override
    public List<FoodItemDTO> getFoodItemsByCategory(String category) {
        return foodItemRepository.findByCategory(category).stream().map(FoodItemMapper::toFoodItemDTO).collect(Collectors.toList());
    }

    @Override
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO, MultipartFile file) {
        ImageResponseDTO imgUrl= imageServiceClient.getImageUrl(file).getBody();
        foodItemDTO.setImageUrl(imgUrl.getImageUrl());
        FoodItem foodItem = FoodItemMapper.toFoodItem(foodItemDTO);
        System.out.println(foodItem);
        return FoodItemMapper.toFoodItemDTO(foodItemRepository.save(foodItem));
    }

    @Override
    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }

    @Override
    public FoodItemDTO updateFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = FoodItemMapper.toFoodItem(foodItemDTO);
        Optional<FoodItem> updatedFoodItem = foodItemRepository.findById(foodItem.getFoodItemId());
        if(updatedFoodItem.isPresent()) {
            updatedFoodItem.get().setName(foodItem.getName());
            updatedFoodItem.get().setDescription(foodItem.getDescription());
            updatedFoodItem.get().setImageUrl(foodItem.getImageUrl());
            updatedFoodItem.get().setPrice(foodItem.getPrice());
            updatedFoodItem.get().setCategory(foodItem.getCategory());
            return FoodItemMapper.toFoodItemDTO(foodItemRepository.save(updatedFoodItem.get()));
        } else {
            return null;
        }
    }

    @Override
    public Boolean isFoodItemExist(Long id) {
        return foodItemRepository.existsById(id);
    }


}
