package com.example.kyrgyzstancentralmedicalcard.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private Long amount;
    private Long companyId;
}
