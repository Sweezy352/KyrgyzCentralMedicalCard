package com.example.kyrgyzstancentralmedicalcard.dto.request;

import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OrganizationRequest(
        @NotNull(message = "Название не может быть пустым")
        @NotBlank(message = "Название не может быть пустым")
        String name,
        @NotNull(message = "Тип организации не может быть пустым")
        OrganizationType type,
        @NotNull(message = "БИН не может быть пустым")
        @NotBlank(message = "БИН не может быть пустым")
        String bin,
        String address,
        String phone,
        String email,
        LocalDate contractStart,
        LocalDate contractEnd
) {}
