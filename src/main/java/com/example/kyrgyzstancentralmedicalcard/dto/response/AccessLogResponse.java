package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.LocalDateTime;

public record AccessLogResponse(
        Long id,
        Long patientId,
        String patientFio,
        Long accessedById,
        String accessedByFio,
        Long organizationId,
        String organizationName,
        String accessType,
        LocalDateTime accessedAt,
        String ipAddress
) {}
