package com.example.slicemate.service.impl;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.CartItemDto;
import com.example.slicemate.repository.CartItemRepository;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.example.slicemate.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartItemServiceImplTest {
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private UserRepository userRepo;
    @Mock
    private FoodItemRepository foodRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CartItemServiceImpl cartService;
    @Test
    void showCart() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        CartItemDto cartItemDto = CartItemDto.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        List<CartItem> items = Mockito.mock(List.class) ;
        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(user));
        when(cartItemRepository.findByUser(Mockito.any(User.class))).thenReturn(items);
//        when(modelMapper.map(cartItem,CartItemDto.class)).thenReturn(cartItemDto);

        List<CartItemDto> itemDtos = cartService.showCart(1);

        assertNotNull(itemDtos);

    }

    @Test
    void addCartItem() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        CartItemDto cartItemDto = CartItemDto.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        when(modelMapper.map(cartItemDto, CartItem.class)).thenReturn(cartItem);
        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(user));
        when(foodRepo.findById(2)).thenReturn(Optional.ofNullable(foodItem));
        when(cartItemRepository.existsByUserAndFoodItem(Mockito.any(User.class),Mockito.any(FoodItem.class))).thenReturn(true);
        when(cartItemRepository.findByUserAndFoodItem(Mockito.any(User.class),Mockito.any(FoodItem.class))).thenReturn(cartItem);
        when(cartItemRepository.save(Mockito.any(CartItem.class))).thenReturn(cartItem);
        when(modelMapper.map(cartItem,CartItemDto.class)).thenReturn(cartItemDto);

        CartItemDto savedCart = cartService.addCartItem(cartItemDto,1,2);

        assertEquals(cartItemDto,savedCart);

    }

    @Test
    void deletecartItem() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();

        when(cartItemRepository.findById(1)).thenReturn(Optional.ofNullable(cartItem));
        assertAll(() -> cartService.deletecartItem(1));
    }

//    @Test
//    void deleteAllItems() {
//        FoodItem foodItem = FoodItem.builder().name("pizaa")
//                .price(230.0).Category("non veg").description("Awesome")
//                .imageUrl("somethingurl").build();
//        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
//        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
//        List<CartItem> items = Mockito.mock(List.class) ;
//        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(user));
//        when(cartItemRepository.findByUser(user)).thenReturn(items);
//        assertAll(() -> );
//    }

    @Test
    void updateItem() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        CartItem cartItem = CartItem.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        CartItemDto cartItemDto = CartItemDto.builder().foodItem(foodItem).user(user).qty(1).price(285.0).build();
        when(cartItemRepository.findById(1)).thenReturn(Optional.ofNullable(cartItem));
        when(cartItemRepository.save(Mockito.any(CartItem.class))).thenReturn(cartItem);
        when(modelMapper.map(cartItem,CartItemDto.class)).thenReturn(cartItemDto);

        CartItemDto updatedItem = cartService.updateItem(cartItemDto,1);

        assertNotNull(updatedItem);
    }
}