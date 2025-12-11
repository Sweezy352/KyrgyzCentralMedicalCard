package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getById(Long id);
    User getByFIO(String fio);
    User getByINN(String inn);
}
