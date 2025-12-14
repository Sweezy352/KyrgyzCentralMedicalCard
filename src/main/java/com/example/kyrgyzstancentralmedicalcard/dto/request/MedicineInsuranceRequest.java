package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInsuranceRequest {
    @NotNull(message = "Название страховки не должно быть пустым")
    @NotBlank(message = "Название страховки не должно быть пустым")
    private String insuranceName;
    @NotNull(message = "Цена страховки не должна быть пустым")
    @NotBlank(message = "Цена страховки не должна быть пустым")
    private BigDecimal price;
    private String description;
}
