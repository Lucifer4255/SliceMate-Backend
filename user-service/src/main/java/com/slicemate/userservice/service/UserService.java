package com.slicemate.userservice.service;

import com.slicemate.userservice.dto.UserDTO;
import com.slicemate.userservice.dto.UserRequestDTO;
import com.slicemate.userservice.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> getUserByUsername(String username);

    UserDTO saveUser(UserRequestDTO userRequestDTO);

    Boolean existsByUserId(Long userId);
}
