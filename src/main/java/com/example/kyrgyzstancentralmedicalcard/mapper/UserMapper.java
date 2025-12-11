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

        Set<Role> roles = Collections.emptySet();
        if (request.getRoleName() != null && !request.getRoleName().isEmpty()) {
            Role role = roleRepository.findByRoleName(request.getRoleName())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRoleName()));
            roles = new HashSet<>(Collections.singleton(role));
        }

        User user = User.builder()
                .inn(request.getInn())
                .fio(request.getFio())
                .password(request.getPassword())
                .roles(roles)
                .build();
        System.out.println("----------> Entity" + user.toString() + "----------> Entity");
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
