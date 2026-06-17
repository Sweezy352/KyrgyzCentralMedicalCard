package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRequest(
        @NotNull(message = "ИНН не должен быть пустым")
        @NotBlank(message = "ИНН не должен быть пустым")
        @Size(max = 14, message = "ИНН должен быть 14 строк")
        String inn,
        @NotNull(message = "ФИО не должно быть пустым")
        @NotBlank(message = "ФИО не должно быть пустым")
        String fio,
        @NotNull(message = "Пароль не должен быть пустым")
        String password,
        String gender,
        LocalDate birthDate,
        String emergencyPhone,
        String bloodGroup,
        String rhFactor
) {}
