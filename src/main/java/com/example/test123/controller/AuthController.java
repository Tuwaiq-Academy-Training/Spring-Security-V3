package com.example.test123.controller;


import com.example.test123.dao.Response;
import com.example.test123.model.User;
import com.example.test123.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @GetMapping("/users")
    public ResponseEntity <List<User>> getUsers(){
        return  ResponseEntity.status(HttpStatus.OK).body(authService.getUsers());
    }


    @GetMapping("/todo")
    public ResponseEntity <List<User>> add(@AuthenticationPrincipal User user){
        System.out.println(user);
        return  ResponseEntity.status(HttpStatus.OK).body(authService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user){
        authService.register(user);
        return  ResponseEntity.status(HttpStatus.OK).body(new Response("New user registered !",201));
    }
}
