package com.example.slicemate.controller;

import com.example.slicemate.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    public void register(@RequestBody User user);
}
