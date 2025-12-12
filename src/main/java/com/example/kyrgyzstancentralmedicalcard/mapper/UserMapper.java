package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;

    @Autowired
    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User toEntity(UserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }

        User user = User.builder()
                .inn(request.getInn())
                .fio(request.getFio())
                .password(request.getPassword())
                .build();
        return user;
    }

    public UserResponse toResponse(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return UserResponse.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .build();
    }
}
