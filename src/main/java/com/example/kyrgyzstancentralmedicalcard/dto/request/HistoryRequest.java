package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HistoryRequest(
        @NotNull(message = "Название не может быть пустым")
        @NotBlank(message = "Название не может быть пустым")
        String name,
        @NotNull(message = "Описание не может быть пустым")
        @NotBlank(message = "Описание не может быть пустым")
        String description
) {}
