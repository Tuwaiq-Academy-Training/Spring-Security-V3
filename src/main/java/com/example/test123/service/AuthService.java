package com.example.test123.service;

import com.example.test123.model.User;
import com.example.test123.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    public List<User> getUsers() {
        return authRepository.findAll();
    }

    public void register(User user) {
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("USER");
        authRepository.save(user);
    }
}
