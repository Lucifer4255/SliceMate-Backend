package com.example.slicemate.controller;

import com.example.slicemate.payloads.AuthRequest;
import com.example.slicemate.payloads.UserDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticateController {
    public ResponseEntity<UserDto> authenticateAndGetToken(AuthRequest authRequest);
}
