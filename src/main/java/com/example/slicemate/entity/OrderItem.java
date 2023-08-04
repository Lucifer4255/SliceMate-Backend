package com.example.slicemate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orderItemId;
    @OneToOne
    private FoodItem foodItem;
    private Integer qty;
    private Double price;
    @ManyToOne
    @JoinColumn(name="user")
    private User user;
    private String address;
    private String orderedAt;
}
