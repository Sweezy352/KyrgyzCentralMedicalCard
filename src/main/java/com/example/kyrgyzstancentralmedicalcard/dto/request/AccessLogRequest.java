package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotNull;

public record AccessLogRequest(
        @NotNull(message = "ID пациента не может быть пустым")
        Long patientId,
        // Необязателен: если не указан — берётся организация текущего пользователя
        Long organizationId,
        @NotNull(message = "Тип события не может быть пустым")
        String accessType
) {}
