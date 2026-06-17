package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.entity.ConsentType;

import java.time.LocalDateTime;

public record ConsentResponse(
        Long id,
        Long organizationId,
        String organizationName,
        ConsentType consentType,
        LocalDateTime grantedAt,
        LocalDateTime expiresAt,
        LocalDateTime revokedAt
) {}
