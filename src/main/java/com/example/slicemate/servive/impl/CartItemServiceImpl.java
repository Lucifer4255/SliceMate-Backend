package com.example.slicemate.servive.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.CartItemDto;
import com.example.slicemate.repository.CartItemRepository;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.repository.UserRepository;
import com.example.slicemate.service.CartItemService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private FoodItemRepository foodRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    public CartItemDto itemToDto(CartItem cartItem){
        CartItemDto CartItemDto = this.modelMapper.map(cartItem,CartItemDto.class);
        return CartItemDto;
    }

    public CartItem dtoToItem(CartItemDto cartItemDto){
        CartItem cartItem = this.modelMapper.map(cartItemDto,CartItem.class);
        return cartItem;
    }


    @Override
    public List<CartItemDto> showCart(Integer id) {
        List<CartItem> items =new ArrayList<>();
        User user =this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        items = this.cartItemRepository.findByUser(user);
        List<CartItemDto> itemDtos = items.stream().map(cartItem -> this.itemToDto(cartItem)).collect(Collectors.toList());
        return itemDtos;
    }

    @Override
    public CartItemDto addCartItem(CartItemDto cartItemDto, Integer userId, Integer foodId) {
        CartItem cartItem = dtoToItem(cartItemDto);
        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        FoodItem foodItem=this.foodRepo.findById(foodId).orElseThrow(() -> new ResourceNotFoundException("FoodItem","id",foodId));
        cartItem.setUser(user);
        cartItem.setFoodItem(foodItem);

        CartItem newCart = this.cartItemRepository.save(cartItem);
        return this.itemToDto(newCart);
    }

    @Override
    public void deletecartItem(Integer id) {
        CartItem cartItem = this.cartItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Item", "id", id));
        this.cartItemRepository.delete(cartItem);
    }

    @Override
    public void deleteAllItems(Integer id) {
        User user =this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user","id",id));
        this.cartItemRepository.deleteAllByUser(user);
    }
}
