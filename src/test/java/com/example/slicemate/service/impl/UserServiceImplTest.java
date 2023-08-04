package com.example.slicemate.service.impl;

import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.UserDto;
import com.example.slicemate.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.example.slicemate.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Test
    void createUser() {
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        UserDto userDto = UserDto.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        when(modelMapper.map(userDto,User.class)).thenReturn(user);
        when(modelMapper.map(user,UserDto.class)).thenReturn(userDto);
        when(userRepo.existsByEmail(Mockito.anyString())).thenReturn(false);
        when(passwordEncoder.encode(Mockito.anyString())).thenReturn("aisygdaysgduyagduasg");
        when(userRepo.save(Mockito.any(User.class))).thenReturn(user);

        UserDto savedUser = userService.createUser(userDto);

        assertEquals(userDto,savedUser);

    }

    @Test
    void getUserbyEmail() {
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        UserDto userDto = UserDto.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        when(userRepo.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(user));
        when(modelMapper.map(user,UserDto.class)).thenReturn(userDto);

        UserDto fetchedUser = userService.getUserbyEmail("example1@gmail.com");
        assertEquals(userDto,fetchedUser);

    }

//    @Test
//    void updateUser() {
//    }
}