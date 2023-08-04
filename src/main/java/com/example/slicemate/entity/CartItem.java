package com.example.slicemate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cartItemId;
    @ManyToOne
    @JoinColumn(name="user")
    private User user;
    @OneToOne
    private FoodItem foodItem;
    private Integer qty;
    private Double price;
}
