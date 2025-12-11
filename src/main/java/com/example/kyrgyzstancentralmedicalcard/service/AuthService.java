package com.example.kyrgyzstancentralmedicalcard.service;

import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    User login(String inn, String password);
    void create(User user);
}
