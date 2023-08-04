package com.example.slicemate.controller.impl;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.payloads.FoodItemDto;
import com.example.slicemate.service.FoodItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FoodItemControllerImplTest {

    @Mock
    private FoodItemService foodItemService;
    @InjectMocks
    private FoodItemControllerImpl foodItemController;
    @Test
    void getFood() {
        FoodItemDto foodItemDto = FoodItemDto.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        List<FoodItemDto> items = Mockito.mock(List.class);
        when(foodItemService.getAllFoodItems(1,6)).thenReturn(items);
        ResponseEntity<List<FoodItemDto>> resp = foodItemController.getFood(1,6);
        assertEquals(HttpStatus.OK,resp.getStatusCode());
        assertNotNull(items);
    }

    @Test
    void updateFood() {
        FoodItemDto foodItemDto = FoodItemDto.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        when(foodItemService.updateFoodItems(Mockito.any(FoodItemDto.class),Mockito.anyInt()))
                .thenReturn(foodItemDto);
        ResponseEntity<FoodItemDto> resp = foodItemController.updateFood(foodItemDto,1);
        assertEquals(HttpStatus.OK,resp.getStatusCode());
        assertEquals(foodItemDto,resp.getBody());

    }

    @Test
    void addFoodItem() {
        FoodItemDto foodItemDto = FoodItemDto.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        when(foodItemService.addFoodItem(Mockito.any(FoodItemDto.class)))
                .thenReturn(foodItemDto);
        ResponseEntity<FoodItemDto> resp = foodItemController.addFoodItem(foodItemDto);
        assertEquals(HttpStatus.CREATED,resp.getStatusCode());
        assertEquals(foodItemDto,resp.getBody());
    }

//    @Test
//    void deleteFood() {
//        FoodItemDto foodItemDto = FoodItemDto.builder().name("pizaa")
//                .price(230.0).Category("non veg").description("Awesome")
//                .imageUrl("somethingurl").build();
//        when(foodItemService.deleteFoodItem(1)).
//    }

    @Test
    void searchFoodbyName() {
        FoodItemDto foodItemDto = FoodItemDto.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        List<FoodItemDto> items = Mockito.mock(List.class);
        when(foodItemService.searchByName(Mockito.anyString())).thenReturn(items);
        ResponseEntity<List<FoodItemDto>> resp = foodItemController.searchFoodbyName("hello");
        assertEquals(HttpStatus.OK,resp.getStatusCode());

    }
}