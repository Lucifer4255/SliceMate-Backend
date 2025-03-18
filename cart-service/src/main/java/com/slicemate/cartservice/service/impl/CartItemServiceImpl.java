package com.slicemate.cartservice.service.impl;

import com.slicemate.cartservice.Feign.FoodClient;
import com.slicemate.cartservice.Feign.UserClient;
import com.slicemate.cartservice.dto.CartItemDTO;
import com.slicemate.cartservice.dto.FoodItemDTO;
import com.slicemate.cartservice.entity.CartItem;
import com.slicemate.cartservice.mapper.CartItemMapper;
import com.slicemate.cartservice.repository.CartItemRepository;
import com.slicemate.cartservice.service.CartItemService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Builder
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private FoodClient foodClient;


    @Override
    public List<CartItemDTO> getCartByUserId(Long userId) {
        if (!userClient.exists(userId)){
            throw new IllegalArgumentException("Invalid User ID: " + userId);
        }
        return cartItemRepository.findByUserId(userId).stream().map(CartItemMapper::toCartItemDTO).toList();
    }


    @Override
    public CartItemDTO addtoCart(CartItemDTO cartItemDTO) {
        if (!userClient.exists(cartItemDTO.getUserId())){
            throw new IllegalArgumentException("Invalid User ID: " + cartItemDTO.getUserId());
        }
        if(!foodClient.exists(cartItemDTO.getFoodItemId())){
            throw new IllegalArgumentException("Invalid Food Item ID: " + cartItemDTO.getFoodItemId());
        }
        CartItem cartItem = cartItemRepository.findByUserIdAndFoodItemId(cartItemDTO.getUserId(), cartItemDTO.getFoodItemId());
        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setPrice(cartItem.getPrice() * cartItem.getQuantity());
        }
        else{
            Optional<ResponseEntity<FoodItemDTO>> fooditem = Optional.ofNullable(foodClient.getFoodItemById(cartItemDTO.getFoodItemId()));
            cartItem = CartItem.builder()
                    .userId(cartItemDTO.getUserId())
                    .foodItemId(cartItemDTO.getFoodItemId())
                    .quantity(1)
                    .Price(Objects.requireNonNull(fooditem.get().getBody()).getPrice())
                    .build();
        }
        return CartItemMapper.toCartItemDTO(cartItemRepository.save(cartItem));

    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }

    @Override
    public Double getTotalPrice(Long userId){
        List<CartItemDTO> cartItems = getCartByUserId(userId);
        double totalPrice = 0;
        for(CartItemDTO cartItem : cartItems){
            Optional<ResponseEntity<FoodItemDTO>> fooditem = Optional.ofNullable(foodClient.getFoodItemById(cartItem.getFoodItemId()));
            if(fooditem.isPresent()){
                totalPrice += Objects.requireNonNull(fooditem.get().getBody()).getPrice() * cartItem.getQuantity();
            }
        }
        return totalPrice;
    }
}
