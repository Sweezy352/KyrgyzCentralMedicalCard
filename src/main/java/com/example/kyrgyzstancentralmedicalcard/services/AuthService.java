package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.dto.response.LoginResponse; // Импортируем LoginResponse
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {
    LoginResponse login(String inn, String password); // Изменено на LoginResponse
    User getCurrentUser();
}
