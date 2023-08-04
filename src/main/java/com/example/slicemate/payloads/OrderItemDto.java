package com.example.slicemate.payloads;

//import java.sql.Date;
import java.util.Date;
import java.sql.Timestamp;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDto {
//	@NotNull
	private Integer orderItemId;
//	@NotNull
	private FoodItem foodItem;
//	@NotNull
	private Integer qty;
//	@NotNull
	private Double price;
//	@NotNull
	private User user;
//	@NotNull
	private String orderedAt;
//	@NotNull
	private String address;
}
