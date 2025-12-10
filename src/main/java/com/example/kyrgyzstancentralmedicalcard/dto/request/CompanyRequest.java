package com.example.kyrgyzstancentralmedicalcard.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private String companyName;
    private String description;
    private Long userId;
}
