package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotNull;

public record AccessLogRequest(
        @NotNull(message = "ID пациента не может быть пустым")
        Long patientId,
        Long organizationId,
        @NotNull(message = "Тип события не может быть пустым")
        String accessType
) {}
