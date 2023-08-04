package com.example.slicemate.service.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.UserDto;
import com.example.slicemate.repository.UserRepository;
import com.example.slicemate.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        System.out.println(this.userRepository.existsByEmail(user.getEmail()));
        if(this.userRepository.existsByEmail(user.getEmail())){
            throw new ResourceAlreadyExistsException("User","email", user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUser(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return this.userToDto(user);
    }

    @Override
    public UserDto getUserbyEmail(String email) {
        User user= userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User","email",email));
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user", "id", id));
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setToken(userDto.getToken());
        User updatedItem = this.userRepository.save(user);
        return this.userToDto(updatedItem);
    }

}
