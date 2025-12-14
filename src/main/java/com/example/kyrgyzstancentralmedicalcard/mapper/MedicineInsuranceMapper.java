package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.MedicineInsuranceRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.MedicineInsuranceResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import org.springframework.stereotype.Component;

@Component
public class MedicineInsuranceMapper {

    public MedicineInsurance toEntity(MedicineInsuranceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return MedicineInsurance.builder()
                .insuranceName(request.getInsuranceName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
    }

    public MedicineInsuranceResponse toResponse(MedicineInsurance entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return MedicineInsuranceResponse.builder()
                .id(entity.getId())
                .insuranceName(entity.getInsuranceName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .build();
    }
}
