package com.example.kyrgyzstancentralmedicalcard.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInsurancesResponse {
    private Long id;
    private MedicineInsuranceResponse medicineInsurance;
    private Long userId;
    private LocalDate dateActive;
    private LocalDate dateExpire;
}
