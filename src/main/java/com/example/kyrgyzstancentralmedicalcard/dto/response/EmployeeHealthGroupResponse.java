package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.LocalDate;

public record EmployeeHealthGroupResponse(
        Long id,
        Long organizationId,
        String organizationName,
        Long patientId,
        String patientFio,
        String employeeId,
        String department,
        String position,
        String healthGroup,
        LocalDate lastCheckupDate,
        LocalDate nextCheckupDate,
        Boolean isActive,
        LocalDate joinedAt
) {}
