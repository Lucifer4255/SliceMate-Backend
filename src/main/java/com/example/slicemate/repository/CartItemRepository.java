package com.example.slicemate.repository;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    public List<CartItem> findByUser(User user);
    public boolean existsByUser(User user);
    public boolean existsByFoodItem(FoodItem foodItem);
    public CartItem findByUserAndFoodItem(User user,FoodItem foodItem);
    public Boolean existsByUserAndFoodItem(User user,FoodItem foodItem);

//    public void deleteAllByUser(User user);
}
