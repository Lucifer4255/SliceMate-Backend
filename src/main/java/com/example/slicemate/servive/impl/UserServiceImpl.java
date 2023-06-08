package com.example.slicemate.servive.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.UserDto;
import com.example.slicemate.repository.UserRepository;
import com.example.slicemate.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        if(this.userRepository.existsById(user.getUserId())){
            throw new ResourceAlreadyExistsException("User","id", user.getUserId());
        }
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

	@Override
	public UserDto getUser(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return this.userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user", "id", id));
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		user.setPhoneNumber(userDto.getPhoneNumber());
		
		User updatedItem = this.userRepository.save(user);
		return this.userToDto(updatedItem);
	}
	
}
