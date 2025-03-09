package com.slicemate.userservice.controllers;

import com.slicemate.userservice.dto.UserDTO;
import com.slicemate.userservice.dto.UserRequestDTO;
import com.slicemate.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(this.userService.saveUser(userRequestDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<UserDTO> userDTO = this.userService.getUserByUsername(username);
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/{id}")
    public Boolean existsByUserId(@PathVariable Long id) {
        return this.userService.existsByUserId(id);
    }
}
