package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;

import java.time.LocalDate;

public record DiagnosisResponse(
        Long id,
        String name,
        String description,
        Long userId,
        UserView userDoc,
        LocalDate dateCreated,
        LocalDate dateUpdated,
        String status
) {}
