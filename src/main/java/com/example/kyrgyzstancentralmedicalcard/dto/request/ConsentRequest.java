package com.example.kyrgyzstancentralmedicalcard.dto.request;

import com.example.kyrgyzstancentralmedicalcard.entity.ConsentType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsentRequest(
        @NotNull(message = "ID организации не может быть пустым")
        Long organizationId,
        @NotNull(message = "Тип согласия не может быть пустым")
        ConsentType consentType,
        LocalDateTime expiresAt
) {}
