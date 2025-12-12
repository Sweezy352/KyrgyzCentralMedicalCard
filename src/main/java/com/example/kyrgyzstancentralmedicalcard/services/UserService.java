package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);
    List<User> getAllUsers();
    User getById(Long id);
    User getByFIO(String fio);
    User getByINN(String inn);
}
