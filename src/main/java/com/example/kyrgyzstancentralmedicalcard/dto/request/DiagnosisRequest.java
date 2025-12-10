package com.example.kyrgyzstancentralmedicalcard.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisRequest {
    private String name;
    private String description;
    private Long userId;
    private Long userDocId;
    private String status;
}
