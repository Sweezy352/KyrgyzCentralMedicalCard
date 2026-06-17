package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewsRequest(
        @NotNull(message = "Название не должно быть пустым")
        @NotBlank(message = "Название не должно быть пустым")
        String name,
        @NotNull(message = "Описание не должно быть пустым")
        @NotBlank(message = "Описание не должно быть пустым")
        String description
) {}
