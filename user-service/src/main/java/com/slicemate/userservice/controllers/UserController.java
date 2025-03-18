package com.slicemate.userservice.controllers;

import com.slicemate.userservice.dto.AuthRequestDTO;
import com.slicemate.userservice.dto.AuthResponseDTO;
import com.slicemate.userservice.dto.UserDTO;
import com.slicemate.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(userService.loginUser(authRequestDTO));
    }
    @GetMapping("/oauth2/google/callback")
    public ResponseEntity<AuthResponseDTO> loginWithGoogle() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof OAuth2AuthenticationToken token)) {
            throw new RuntimeException("Invalid token");
        }
        String email = token.getPrincipal().getAttribute("email");
        String name = token.getPrincipal().getAttribute("name");
        return ResponseEntity.ok(userService.loginwithGoogle(name,email));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<UserDTO> userDTO = this.userService.getUserByUsername(username);
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/exists/{id}")
    public Boolean existsByUserId(@PathVariable Long id) {
        return this.userService.existsByUserId(id);
    }
}
