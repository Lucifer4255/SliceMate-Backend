package com.example.slicemate.repository;

import com.example.slicemate.entity.FoodItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {
	public List<FoodItem> findByNameContaining(String name);
}
