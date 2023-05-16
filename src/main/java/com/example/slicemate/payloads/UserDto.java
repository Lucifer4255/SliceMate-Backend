package com.example.slicemate.payloads;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.OrderItem;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private Long phoneNumber;
    private List<CartItem> cartItemList;
    private List<OrderItem> orderItemList;
}
