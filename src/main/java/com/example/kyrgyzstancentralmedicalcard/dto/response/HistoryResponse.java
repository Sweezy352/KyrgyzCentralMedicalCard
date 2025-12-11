package com.example.kyrgyzstancentralmedicalcard.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Long userDocId;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
}
