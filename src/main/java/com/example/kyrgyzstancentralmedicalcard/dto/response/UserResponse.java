package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String fio,
        String inn,
        String gender,
        LocalDate birthDate,
        String emergencyPhone,
        String bloodGroup,
        String rhFactor
) {}
