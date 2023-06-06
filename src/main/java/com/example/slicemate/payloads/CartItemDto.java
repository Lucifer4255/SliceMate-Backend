package com.example.slicemate.payloads;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private Integer cartItemId;
//	@NotNull
@JsonManagedReference
	private User user;
//	@NotNull
@JsonManagedReference
	private FoodItem foodItem;
	@NotNull
	private Integer qty;
	@NotNull
	private Double price;
}
