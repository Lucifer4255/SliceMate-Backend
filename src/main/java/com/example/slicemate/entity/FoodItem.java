package com.example.slicemate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer FoodItemId;
    private String name;
    private String description;
    private String imageUrl;
    private String Category;
    private Double price;
}
