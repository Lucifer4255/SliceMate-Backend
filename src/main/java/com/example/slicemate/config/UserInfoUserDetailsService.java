package com.example.slicemate.config;

import com.example.slicemate.entity.User;
import com.example.slicemate.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findByEmail(username);
        return user.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException(username));
//        return mapper.map(user,UserInfoUserDetails.class);
    }

}
