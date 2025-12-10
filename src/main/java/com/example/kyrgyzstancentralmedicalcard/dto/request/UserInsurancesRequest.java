package com.example.kyrgyzstancentralmedicalcard.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInsurancesRequest {
    private Long medicineInsuranceId;
    private Long userId;
}
