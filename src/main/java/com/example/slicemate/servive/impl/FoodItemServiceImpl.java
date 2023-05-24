package com.example.slicemate.servive.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.FoodItemDto;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.service.FoodItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FoodItemDto itemToDto(FoodItem foodItem){
        FoodItemDto foodItemDto = this.modelMapper.map(foodItem,FoodItemDto.class);
        return foodItemDto;
    }

    public FoodItem dtoToItem(FoodItemDto foodItemDto){
        FoodItem foodItem = this.modelMapper.map(foodItemDto,FoodItem.class);
        return foodItem;
    }
    @Override
    public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
        FoodItem foodItem = this.dtoToItem(foodItemDto);
        if(foodItemRepository.existsById(foodItem.getFoodItemId())) {
        	throw new ResourceAlreadyExistsException("Food Item","id",foodItem.getFoodItemId());
        }
        else {
        FoodItem savedItem = this.foodItemRepository.save(foodItem);
        return this.itemToDto(savedItem);
        }
    }

    @Override
    public List<FoodItemDto> getAllFoodItems() {
        List<FoodItem> items =(List<FoodItem>) foodItemRepository.findAll();
        List<FoodItemDto> itemDtos = items.stream().map(foodItem -> this.itemToDto(foodItem)).collect(Collectors.toList());
        return itemDtos;
    }

    @Override
    public FoodItemDto updateFoodItems(FoodItemDto foodItemDto, Integer id) {
        FoodItem foodItem = this.foodItemRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Food Item","id",id));
        foodItem.setFoodItemId(foodItemDto.getFoodItemId());
        foodItem.setCartItem(foodItemDto.getCartItem());
        foodItem.setOrderItem(foodItemDto.getOrderItem());
        foodItem.setName(foodItemDto.getName());
        foodItem.setCategory(foodItemDto.getCategory());
        foodItem.setDescription(foodItemDto.getDescription());
        foodItem.setPrice(foodItemDto.getPrice());
        foodItem.setImageUrl(foodItemDto.getImageUrl());

        FoodItem updatedItem = this.foodItemRepository.save(foodItem);
        return this.itemToDto(updatedItem);
    }

    @Override
    public void deleteFoodItem(Integer id) {
        FoodItem foodItem = this.foodItemRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Food Item","id",id));
        this.foodItemRepository.delete(foodItem);
    }

}
