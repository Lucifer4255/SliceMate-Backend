package com.example.slicemate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="FOOD_SEQ")
    @SequenceGenerator(name="FOOD_SEQ",sequenceName="FOOD_SEQ",allocationSize=1)
    private Integer FoodItemId;
    private String name;
    private String description;
    private String imageUrl;
    private String Category;
    private Double price;
    @OneToOne(mappedBy = "foodItem",cascade = CascadeType.ALL)
    @JsonBackReference
    private CartItem cartItem;
    @OneToOne(mappedBy = "foodItem")
    @JsonBackReference
    private OrderItem orderItem;
}
