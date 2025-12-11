package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.MedicineInsuranceRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.MedicineInsuranceResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import org.springframework.stereotype.Component;

@Component
public class MedicineInsuranceMapper extends BaseMapper<MedicineInsurance, MedicineInsuranceRequest, MedicineInsuranceResponse> {

    @Override
    public MedicineInsurance toEntity(MedicineInsuranceRequest request) {
        return mapFields(request, new MedicineInsurance());
    }

    @Override
    public MedicineInsuranceResponse toResponse(MedicineInsurance entity) {
        return mapFields(entity, new MedicineInsuranceResponse());
    }
}
