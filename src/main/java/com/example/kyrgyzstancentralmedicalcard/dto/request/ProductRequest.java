package com.example.kyrgyzstancentralmedicalcard.dto.request;

public record ProductRequest(
        String productName,
        Long amount,
        Long companyId
) {}
