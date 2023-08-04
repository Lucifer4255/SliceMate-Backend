package com.example.slicemate.service.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.FoodItemDto;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.service.FoodItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {
	@Autowired
	private FoodItemRepository foodItemRepository;

	@Autowired
	private ModelMapper modelMapper;

	public FoodItemDto itemToDto(FoodItem foodItem) {
		FoodItemDto foodItemDto = this.modelMapper.map(foodItem, FoodItemDto.class);
		return foodItemDto;
	}

	public FoodItem dtoToItem(FoodItemDto foodItemDto) {
		FoodItem foodItem = this.modelMapper.map(foodItemDto, FoodItem.class);
		return foodItem;
	}

	@Override
	public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
		FoodItem foodItem = this.dtoToItem(foodItemDto);
		if (foodItemRepository.existsByName(foodItem.getName())) {
			throw new ResourceAlreadyExistsException("Food Item", "name", foodItem.getName());
		} else {
			FoodItem savedItem = this.foodItemRepository.save(foodItem);
			return this.itemToDto(savedItem);
		}
	}

	@Override
	public List<FoodItemDto> getAllFoodItems(Integer pageNumber,Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber,pageSize);
		Page<FoodItem> pageItems= foodItemRepository.findAll(p);
		List<FoodItem> items = (List<FoodItem>) pageItems.getContent();
		List<FoodItemDto> itemDtos = items.stream().map(foodItem -> this.itemToDto(foodItem))
				.collect(Collectors.toList());
		return itemDtos;
	}

	@Override
	public FoodItemDto updateFoodItems(FoodItemDto foodItemDto, Integer id) {
		FoodItem foodItem = this.foodItemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Food Item", "id", id));
//		foodItem.setFoodItemId(foodItemDto.getFoodItemId());
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
		FoodItem foodItem = this.foodItemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Food Item", "id", id));
		this.foodItemRepository.delete(foodItem);
	}

	@Override
	public FoodItemDto getFoodItem(Integer id) {
		FoodItem foodItem = this.foodItemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("food Item", "id", id));
		return this.itemToDto(foodItem);
	}

	@Override
	public List<FoodItemDto> searchByName(String name) {
		// TODO Auto-generated method stub
		List<FoodItem> items = new ArrayList<>();
		items = this.foodItemRepository.findByNameContaining(name);
		List<FoodItemDto> itemDtos = items.stream().map(foodItem -> this.itemToDto(foodItem))
				.collect(Collectors.toList());
		return itemDtos;
	}

}
