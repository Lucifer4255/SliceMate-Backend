package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.UserController;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.UserDto;
import com.example.slicemate.service.JwtService;
import com.example.slicemate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
	@Autowired
	private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
		String token = jwtService.generateToken(createdUser.getEmail());
		createdUser.setToken(token);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserDto> getUserDetails(@PathVariable Integer id) {
		System.out.println("ok");
		return ResponseEntity.ok(userService.getUser(id));
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDto> updateUserDetails(@RequestBody UserDto userDto, Integer id) {
		UserDto updatedUser = this.userService.updateUser(userDto,id);
		return ResponseEntity.ok(updatedUser);
	}
}
