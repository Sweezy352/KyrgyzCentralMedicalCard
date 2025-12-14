package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.LoginRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.LoginResponse; // Импортируем LoginResponse
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.mapper.UserMapper;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private UserMapper userMapper;
    private UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserMapper userMapper, UserService userService) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){ // Изменено на LoginResponse
        return new ResponseEntity<>(authService.login(request.getInn(), request.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody UserRequest user){
        userService.create(userMapper.toEntity(user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-current")
    public ResponseEntity<UserResponse> getCurrent(){
        return ResponseEntity.ok(userMapper.toResponse(authService.getCurrentUser()));
    }
}
