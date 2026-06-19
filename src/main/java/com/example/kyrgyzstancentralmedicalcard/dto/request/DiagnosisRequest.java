package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DiagnosisRequest(
        @NotNull(message = "Название диагноза не может быть пустым")
        @NotBlank(message = "Название диагноза не может быть пустым")
        String name,
        @NotNull(message = "Описание диагноза не может быть пустым")
        @NotBlank(message = "Описание диагноза не может быть пустым")
        String description
) {}
