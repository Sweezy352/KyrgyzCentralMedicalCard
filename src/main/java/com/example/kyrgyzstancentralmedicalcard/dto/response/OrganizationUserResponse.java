package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;

import java.time.LocalDateTime;

public record OrganizationUserResponse(
        Long id,
        UserView user,
        String role,
        String department,
        Boolean isActive,
        LocalDateTime joinedAt
) {}
