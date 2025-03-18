package com.slicemate.userservice.service;

import com.slicemate.userservice.dto.AuthRequestDTO;
import com.slicemate.userservice.dto.AuthResponseDTO;
import com.slicemate.userservice.dto.UserDTO;
//import com.slicemate.userservice.dto.UserRequestDTO;
import com.slicemate.userservice.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> getUserByUsername(String username);

    public AuthResponseDTO register(UserDTO userDTO);

    public AuthResponseDTO loginUser(AuthRequestDTO authRequestDTO);

    public AuthResponseDTO loginwithGoogle(String name, String email);

    public void validateToken(String token);

    Boolean existsByUserId(Long userId);
}
