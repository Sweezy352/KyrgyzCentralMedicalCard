package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Role;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper extends BaseMapper<User, UserRequest, UserResponse> {

    private RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public User toEntity(UserRequest request) {
        User entity = mapFields(request, new User());

        if (request.getRoleName() != null && !request.getRoleName().isEmpty()) {
            Role role = roleRepository.findByRoleName(request.getRoleName())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRoleName()));
            Set<Role> roles = new HashSet<>(Collections.singleton(role));
            entity.setRoles(roles);
        }
        return entity;
    }

    @Override
    public UserResponse toResponse(User entity) {
        return mapFields(entity, new UserResponse());
    }
}
