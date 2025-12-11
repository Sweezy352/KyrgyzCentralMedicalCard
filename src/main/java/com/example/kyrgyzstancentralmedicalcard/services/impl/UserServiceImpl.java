package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого пользователя не существует"));
    }

    @Override
    public User getByFIO(String fio) {
        return userRepository.findByFio(fio).orElseThrow(() -> new RuntimeException("Такого пользователя не существует"));
    }

    @Override
    public User getByINN(String inn) {
        return userRepository.findByInn(inn).orElseThrow(() -> new RuntimeException("Такого пользователя не существует"));
    }
}
