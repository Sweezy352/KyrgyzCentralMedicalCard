package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;

import java.time.LocalDate;

public record AllergieDtoResponse(
        Long id,
        String name,
        String description,
        UserView userViewDoc,
        LocalDate dateCreated,
        LocalDate dateUpdated,
        Boolean status
) {}
