package com.example.slicemate.repository;

import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    public List<OrderItem> findByUser(User user);
//    public Boolean existsByUser(Integer id);
}
