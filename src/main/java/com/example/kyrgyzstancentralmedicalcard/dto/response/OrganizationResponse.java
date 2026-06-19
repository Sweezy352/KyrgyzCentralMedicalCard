package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrganizationResponse(
        Long id,
        String name,
        OrganizationType type,
        String bin,
        String address,
        String phone,
        String email,
        Boolean isActive,
        LocalDate contractStart,
        LocalDate contractEnd,
        LocalDateTime createdAt
) {}
