package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.LocalDate;

public record NewsResponse(
        Long id,
        String name,
        String description,
        LocalDate dateCreated,
        Long userId
) {}
