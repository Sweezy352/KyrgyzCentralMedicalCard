package com.example.kyrgyzstancentralmedicalcard.dto.request;

public record CompanyRequest(
        String companyName,
        String description,
        Long userId
) {}
