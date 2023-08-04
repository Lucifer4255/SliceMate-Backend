package com.example.slicemate.controller.impl;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.CartItemDto;
import com.example.slicemate.service.CartItemService;
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

import static com.example.slicemate.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartItemControllerImplTest {

    @Mock
    private CartItemService cartItemService;
    @InjectMocks
    private CartItemControllerImpl cartItemController;
    @Test
    void showCartItems() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
//        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        CartItemDto cartItemDto = CartItemDto.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        List<CartItemDto> items = Mockito.mock(List.class) ;
        when(cartItemService.showCart(1)).thenReturn(items);
        ResponseEntity<List<CartItemDto>> resp= cartItemController.showCartItems(1);
        assertEquals(HttpStatus.OK,resp.getStatusCode());

    }

    @Test
    void addToCart() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
//        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        CartItemDto cartItemDto = CartItemDto.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        when(cartItemService.addCartItem(Mockito.any(CartItemDto.class),Mockito.anyInt(),Mockito.anyInt()))
                .thenReturn(cartItemDto);
        ResponseEntity<CartItemDto> resp = cartItemController.addToCart(cartItemDto,1,2);
        assertEquals(HttpStatus.CREATED,resp.getStatusCode());
    }

    @Test
    void updateCartItem() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
//        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        CartItemDto cartItemDto = CartItemDto.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        when(cartItemService.updateItem(Mockito.any(CartItemDto.class),Mockito.anyInt())).thenReturn(cartItemDto);
        ResponseEntity<CartItemDto> resp = cartItemController.updateCartItem(cartItemDto,1);
        assertEquals(HttpStatus.OK,resp.getStatusCode());
    }

//    @Test
//    void deleteItem() {
//    }
//
//    @Test
//    void emptyCart() {
//    }
}