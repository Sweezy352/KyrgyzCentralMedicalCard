package com.example.kyrgyzstancentralmedicalcard.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInsuranceResponse {
    private Long id;
    private String insuranceName;
    private String description;
    private BigDecimal price;
}
