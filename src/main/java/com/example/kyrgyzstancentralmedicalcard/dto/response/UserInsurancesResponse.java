package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.LocalDate;

public record UserInsurancesResponse(
        Long id,
        Long medicineInsuranceId,
        Long userId,
        LocalDate dateActive,
        LocalDate dateExpire
) {}
