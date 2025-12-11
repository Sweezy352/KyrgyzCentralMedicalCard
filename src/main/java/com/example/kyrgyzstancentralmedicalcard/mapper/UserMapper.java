package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Role;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
                //Роли будут выдаваться автоматически, для обычного пользователя USER, но для студента который окончий мед универ автоматически роль DOCTOR и тд
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
