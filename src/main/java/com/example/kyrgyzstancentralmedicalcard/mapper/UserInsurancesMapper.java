package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserInsurancesRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserInsurancesResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import com.example.kyrgyzstancentralmedicalcard.repository.MedicineInsuranceRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserInsurancesMapper {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected MedicineInsuranceRepository insuranceRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "medicineInsurance", ignore = true)
    @Mapping(target = "dateActive", ignore = true)
    @Mapping(target = "dateExpire", ignore = true)
    @Mapping(target = "active", ignore = true)
    public abstract UserInsurances toEntity(UserInsurancesRequest request);

    @AfterMapping
    protected void setRelations(UserInsurancesRequest request, @MappingTarget UserInsurances entity) {
        entity.setUser(userRepository.findById(request.userId()).orElseThrow());
        entity.setMedicineInsurance(insuranceRepository.findById(request.medicineInsuranceId()).orElseThrow());
    }

    @Mapping(target = "userId", expression = "java(entity.getUser().getId())")
    @Mapping(target = "medicineInsuranceId", expression = "java(entity.getMedicineInsurance().getId())")
    public abstract UserInsurancesResponse toResponse(UserInsurances entity);
}
