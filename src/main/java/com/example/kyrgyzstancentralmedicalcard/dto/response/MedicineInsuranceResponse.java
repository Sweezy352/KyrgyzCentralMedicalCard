package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.math.BigDecimal;

public record MedicineInsuranceResponse(
        Long id,
        String insuranceName,
        BigDecimal price
) {}
