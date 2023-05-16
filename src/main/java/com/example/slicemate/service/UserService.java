package com.example.slicemate.service;

import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.UserDto;

public interface UserService {
    public UserDto createUser(UserDto userDto);
}
