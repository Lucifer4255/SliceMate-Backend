package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.AuthenticateController;
import com.example.slicemate.payloads.AuthRequest;
import com.example.slicemate.payloads.UserDto;
import com.example.slicemate.service.JwtService;
import com.example.slicemate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class AuthControllerImpl implements AuthenticateController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/authenticate")
    public ResponseEntity<UserDto> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(auth.isAuthenticated()){

            String token = jwtService.generateToken(authRequest.getUsername());
            UserDto authUser = this.userService.getUserbyEmail(authRequest.getUsername());
            authUser.setToken(token);
            return ResponseEntity.ok(authUser);
        }
        else{
            throw new UsernameNotFoundException("Invalid User Request");
        }
    }
}
