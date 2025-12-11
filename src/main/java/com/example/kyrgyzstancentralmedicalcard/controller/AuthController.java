package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(String inn, String password){
        authService.login(inn, password);
        return new ResponseEntity<>("Авторизация прошла успешно", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> create(User user){
        authService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
