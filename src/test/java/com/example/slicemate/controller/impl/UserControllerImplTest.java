package com.example.slicemate.controller.impl;

import com.example.slicemate.payloads.UserDto;
import com.example.slicemate.service.JwtService;
import com.example.slicemate.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.slicemate.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private UserControllerImpl userController;
    @Test
    void register() {
        UserDto userDto = UserDto.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        when(jwtService.generateToken(Mockito.anyString())).thenReturn("askkdhaksjhd");
        when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(userDto);
        ResponseEntity<UserDto> resp = userController.register(userDto);
        assertEquals(HttpStatus.CREATED,resp.getStatusCode());
        assertEquals(userDto,resp.getBody());
    }

//    @Test
//    void getUserDetails() {
//
//    }

//    @Test
//    void updateUserDetails() {
//
//    }
}