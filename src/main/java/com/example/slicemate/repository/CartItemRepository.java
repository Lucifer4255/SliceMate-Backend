package com.example.slicemate.repository;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    public List<CartItem> findByUser(User user);
//    public boolean existsByUser(Integer id);
//    public void deleteAllByUser(User user);
}
