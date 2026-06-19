package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.LocalDate;

public record CompanyResponse(
        Long id,
        String companyName,
        String description,
        Long userId,
        LocalDate dateCreated
) {}
