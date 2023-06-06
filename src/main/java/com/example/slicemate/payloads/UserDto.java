package com.example.slicemate.payloads;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    @NotNull
    private Integer userId;
    @NotEmpty
    @Size(min = 4,message = "Name must be 4 characters atleast")
    private String name;
    @NotNull
    @Email(message = "Email is not correct")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Password must contain Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
    @NotNull
    private String role;
    @NotNull
//    @Range(min=10,max=10,message = "Phone number must be valid")
    private Long phoneNumber;
    @JsonBackReference
    private List<CartItem> cartItemList;
    @JsonBackReference
    private List<OrderItem> orderItemList;
}
