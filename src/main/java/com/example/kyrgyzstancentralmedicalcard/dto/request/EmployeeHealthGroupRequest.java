package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeHealthGroupRequest(
        @NotNull(message = "ID пациента не может быть пустым")
        Long patientId,
        Long organizationId,
        String employeeId,
        String department,
        String position,
        String healthGroup,
        LocalDate lastCheckupDate,
        LocalDate nextCheckupDate,
        LocalDate joinedAt
) {}
