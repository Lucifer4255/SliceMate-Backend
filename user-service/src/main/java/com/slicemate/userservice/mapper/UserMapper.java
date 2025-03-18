package com.slicemate.userservice.mapper;

import com.slicemate.userservice.dto.UserDTO;
//import com.slicemate.userservice.dto.UserRequestDTO;
import com.slicemate.userservice.entity.User;
import com.slicemate.userservice.entity.UserRole;

public class UserMapper {
    public static UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .build();
    }
    public static User userDTOtoUser(UserDTO userDTO){
        return User.builder()
                .userId(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .role(UserRole.valueOf(userDTO.getRole()))
                .build();
    }
}
