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
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="ORDER_SEQ")
    @SequenceGenerator(name="ORDER_SEQ",sequenceName="ORDER_SEQ",allocationSize=1)
    private Integer orderItemId;
    @OneToOne
    @JsonManagedReference
    private FoodItem foodItem;
    private Integer qty;
    private Double price;
    @ManyToOne
    @JsonManagedReference
    private User user;
    private Timestamp orderedAt;
}
