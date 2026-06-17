package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.ZonedDateTime;

public record ProductResponse(
        Long id,
        String productName,
        Long amount,
        ZonedDateTime dateCreated,
        ZonedDateTime dateUpdated,
        Long companyId
) {}
