package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;

import java.time.LocalDate;

public record ReceiptDtoResponse(
        Long id,
        String number,
        String name,
        String description,
        UserView userViewDoc,
        LocalDate dateCreated
) {}
