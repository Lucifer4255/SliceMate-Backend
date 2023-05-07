package com.example.slicemate.servive.impl;

import com.example.slicemate.entity.User;
import com.example.slicemate.repository.UserRepository;
import com.example.slicemate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void addUser(User user) {
        userRepository.save(user);

    }
}
