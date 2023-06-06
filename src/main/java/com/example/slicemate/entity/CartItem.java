package com.example.slicemate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="CART_SEQ")
    @SequenceGenerator(name="CART_SEQ",sequenceName="CART_SEQ",allocationSize=1)
    private Integer cartItemId;
    @ManyToOne
//    @JsonManagedReference
    @JoinColumn(name="user")
    private User user;
    @OneToOne
//    @JsonManagedReference
    private FoodItem foodItem;
    private Integer qty;
    private Double price;
}
