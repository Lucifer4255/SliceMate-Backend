package com.example.slicemate.payloads;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemDto {
	private Integer cartItemId;
	private User user;
	private FoodItem foodItem;
	private Integer qty;
	private Double price;
}
