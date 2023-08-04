package com.example.slicemate.service.impl;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.FoodItemDto;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.service.FoodItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FoodItemServiceImplTest {

    @Mock
    private FoodItemRepository foodRepo;

    @InjectMocks
    private FoodItemServiceImpl foodService;

    @Mock
    private ModelMapper modelMapper;
    @Test
    void FoodItemService_addFoodItem_returnsFoodItemDto() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();

        FoodItemDto foodItemDto =FoodItemDto.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        when(modelMapper.map(foodItemDto, FoodItem.class)).thenReturn(foodItem);
        when(modelMapper.map(foodItem, FoodItemDto.class)).thenReturn(foodItemDto);
        when(foodRepo.save(Mockito.any(FoodItem.class))).thenReturn(foodItem);

        FoodItemDto saveFood = foodService.addFoodItem(foodItemDto);

        assertNotNull(saveFood);
    }

    @Test
    void getAllFoodItems() {
        Page<FoodItem> foodItems = Mockito.mock(Page.class);
        when(foodRepo.findAll(Mockito.any(Pageable.class))).thenReturn(foodItems);

        List<FoodItemDto> fetchItems = foodService.getAllFoodItems(1,6);
        assertNotNull(fetchItems);
    }

    @Test
    void updateFoodItems() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();

        FoodItemDto foodItemDto =FoodItemDto.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
//        when(modelMapper.map(foodItemDto, FoodItem.class)).thenReturn(foodItem);
        when(modelMapper.map(foodItem, FoodItemDto.class)).thenReturn(foodItemDto);
        when(foodRepo.findById(1)).thenReturn(Optional.ofNullable(foodItem));
        when(foodRepo.save(Mockito.any(FoodItem.class))).thenReturn(foodItem);
        FoodItemDto updatedFood = foodService.updateFoodItems(foodItemDto,1);

        assertEquals(foodItemDto,updatedFood);


    }

    @Test
    void deleteFoodItem() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        when(foodRepo.findById(1)).thenReturn(Optional.ofNullable(foodItem));

        assertAll( ()-> foodService.deleteFoodItem(1));
    }

    @Test
    void getFoodItem() {


    }

    @Test
    void searchByName() {
        List<FoodItem> items = Mockito.mock(List.class);
        when(foodRepo.findByNameContaining("hello")).thenReturn(items);

        List<FoodItemDto> itemDtos = foodService.searchByName("hello");
        assertNotNull(itemDtos);

    }
}