package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MedicineInsuranceRequest(
        @NotNull(message = "Название страховки не должно быть пустым")
        String insuranceName,
        @NotNull(message = "Цена страховки не должна быть пустой")
        BigDecimal price
) {}
