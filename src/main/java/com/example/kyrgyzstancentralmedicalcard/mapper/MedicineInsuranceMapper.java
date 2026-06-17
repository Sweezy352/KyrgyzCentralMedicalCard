package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.MedicineInsuranceRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.MedicineInsuranceResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class MedicineInsuranceMapper {

    @Mapping(target = "id", ignore = true)
    public abstract MedicineInsurance toEntity(MedicineInsuranceRequest request);

    public abstract MedicineInsuranceResponse toResponse(MedicineInsurance entity);
}
