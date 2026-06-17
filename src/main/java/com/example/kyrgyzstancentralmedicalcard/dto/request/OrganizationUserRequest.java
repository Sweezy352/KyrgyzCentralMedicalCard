package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotNull;

public record OrganizationUserRequest(
        @NotNull(message = "ID пользователя не может быть пустым")
        Long userId,
        @NotNull(message = "Роль не может быть пустой")
        String role,
        String department
) {}
