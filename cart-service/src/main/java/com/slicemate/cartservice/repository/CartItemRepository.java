package com.slicemate.cartservice.repository;

import com.slicemate.cartservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    CartItem findByUserIdAndFoodItemId(Long userId, Long foodItemId);
    void deleteByUserId(Long userId);
}
