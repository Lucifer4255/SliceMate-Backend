package com.example.slicemate.payloads;

import java.sql.Timestamp;

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
public class OrderItemDto {
	private Integer orderItemId;
	private FoodItem foodItem;
	private Integer qty;
	private Double price;
	private User user;
	private Timestamp orderedAt;
}
