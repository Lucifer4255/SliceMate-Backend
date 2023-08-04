package com.example.slicemate.payloads;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartItemDto {
//	@NotNull
	private Integer cartItemId;
//	@NotNull
	private User user;
//	@NotNull

	private FoodItem foodItem;
	@NotNull
	private Integer qty;
	@NotNull
	private Double price;
}
