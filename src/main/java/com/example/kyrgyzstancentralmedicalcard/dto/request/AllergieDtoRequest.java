package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AllergieDtoRequest(
        @NotNull(message = "Не может быть пустым")
        @NotBlank(message = "Не может быть пустым")
        String name,
        @NotNull(message = "Не может быть пустым")
        @NotBlank(message = "Не может быть пустым")
        String description
) {}
