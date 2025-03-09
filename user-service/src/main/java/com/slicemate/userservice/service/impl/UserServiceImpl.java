package com.slicemate.userservice.service.impl;

import com.slicemate.userservice.dto.UserDTO;
import com.slicemate.userservice.dto.UserRequestDTO;
import com.slicemate.userservice.entity.User;
import com.slicemate.userservice.mapper.UserMapper;
import com.slicemate.userservice.repository.UserRepository;
import com.slicemate.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).map(UserMapper::toUserDTO);
    }
    @Override
    public UserDTO saveUser(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toUserEntity(userRequestDTO);
        return UserMapper.toUserDTO(this.userRepository.save(user));
    }

    @Override
    public Boolean existsByUserId(Long userId) {
        return userRepository.existsById(userId);
    }
}
