package com.slicemate.userservice.service.impl;

import com.slicemate.userservice.dto.AuthRequestDTO;
import com.slicemate.userservice.dto.AuthResponseDTO;
import com.slicemate.userservice.dto.UserDTO;
import com.slicemate.userservice.entity.User;
import com.slicemate.userservice.entity.UserRole;
import com.slicemate.userservice.mapper.UserMapper;
import com.slicemate.userservice.repository.UserRepository;
import com.slicemate.userservice.security.JWTUtil;
import com.slicemate.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toUserDTO);
    }

    @Override
    public AuthResponseDTO register(UserDTO userDTO) {
        if (userRepository.findByEmailOrUsername(userDTO.getEmail(), userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = UserMapper.userDTOtoUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // Add role as a claim in the JWT token
        List<String> roles = Collections.singletonList(user.getRole().name());
        String token = jwtUtil.generateToken(roles,user.getUsername());
        return AuthResponseDTO.builder().token(token).build();
//        return AuthResponseDTO.builder().token(token).build();
    }

    @Override
    public AuthResponseDTO loginUser(AuthRequestDTO authRequestDTO) {
        Optional<User> user = userRepository.findByEmailOrUsername(authRequestDTO.getUsernameOrEmail(), authRequestDTO.getUsernameOrEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid username or email");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsernameOrEmail(), authRequestDTO.getPassword())
        );
        if (authenticate.isAuthenticated()) {
            // Add role as a claim in the JWT token
            List<String> roles = Collections.singletonList(user.get().getRole().name());
            String token = jwtUtil.generateToken(roles,user.get().getUsername());
            return AuthResponseDTO.builder().token(token).build();
//            return AuthResponseDTO.builder().token(token).build();
        } else {
            throw new RuntimeException("Invalid access");
        }
    }

    @Override
    public AuthResponseDTO loginwithGoogle(String name, String email) {
        Optional<User> userOptional = userRepository.findByEmailOrUsername(email, name);
        User user = userOptional.orElseGet(() -> userRepository.save(User.builder()
                .username(name)
                .password(passwordEncoder.encode("google-login"))
                .email(email)
                .role(UserRole.USER) // Default role for Google users
                .build()));

        // Add role as a claim in the JWT token
        List<String> roles = Collections.singletonList(user.getRole().name());
        String token = jwtUtil.generateToken(roles,user.getUsername());
        return AuthResponseDTO.builder().token(token).build();}

    @Override
    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }

    @Override
    public Boolean existsByUserId(Long userId) {
        return userRepository.existsById(userId);
    }
}