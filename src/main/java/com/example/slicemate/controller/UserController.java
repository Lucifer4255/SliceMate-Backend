package com.example.slicemate.controller;

import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto);
    public ResponseEntity<UserDto> getUserDetails(@PathVariable Integer id);
    public ResponseEntity<UserDto> updateUserDetails(@RequestBody UserDto userDto,@PathVariable Integer id);

}
