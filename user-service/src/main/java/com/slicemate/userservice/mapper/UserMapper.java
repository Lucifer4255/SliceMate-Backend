package com.slicemate.userservice.mapper;

import com.slicemate.userservice.dto.UserDTO;
import com.slicemate.userservice.dto.UserRequestDTO;
import com.slicemate.userservice.entity.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    public static User toUserEntity(UserRequestDTO userRequestDTO){
        return User.builder()
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .email(userRequestDTO.getEmail())
                .role(userRequestDTO.getRole() != null ? userRequestDTO.getRole() : "USER")
                .build();
    }
}
