package com.example.kyrgyzstancentralmedicalcard.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInsuranceRequest {
    private String insuranceName;
    private BigDecimal price;
}
