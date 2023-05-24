package com.example.slicemate.servive.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.CartItem;
import com.example.slicemate.payloads.CartItemDto;
import com.example.slicemate.payloads.FoodItemDto;
import com.example.slicemate.repository.CartItemRepository;
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
    public List<CartItemDto> showCart(String id) {
        List<CartItem> items =new ArrayList<>();
        if(cartItemRepository.existsByUser(id)) {
        	items = this.cartItemRepository.findByUser(id);
        	List<CartItemDto> itemDtos = items.stream().map(foodItem -> this.itemToDto(foodItem)).collect(Collectors.toList());
        	return itemDtos;
        }
        else {
        	throw new ResourceNotFoundException("Cart Items", " user id", id);
        }
    }

    @Override
    public CartItemDto addCartItem(CartItemDto cartItemDto) {
    	CartItem cartItem = dtoToItem(cartItemDto);
    	if(cartItemRepository.existsById(cartItem.getCartItemId())) {
    		throw new ResourceAlreadyExistsException("Cart Item","id",cartItem.getCartItemId());
    	}
    	else {
    		CartItem addedItem = this.cartItemRepository.save(cartItem);
    		return this.itemToDto(addedItem);
    	}
    }

    @Override
    public void deletecartItem(Integer id) {
        CartItem cartItem = this.cartItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Item", "id", id));
        this.cartItemRepository.delete(cartItem);
    }

    @Override
    public void deleteAllItems(String id) {
    	if(cartItemRepository.existsByUser(id)) {
    		this.cartItemRepository.deleteAllByUser(id);    		
    	}
    	else throw new ResourceNotFoundException("Cart of the User", "id", id);
    }
}
