package com.example.test123.service;

import com.example.test123.model.User;
import com.example.test123.repository.AuthRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {


    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=authRepository.findUserByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return user.get();
    }
}
