package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserInsurancesRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserInsurancesResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import com.example.kyrgyzstancentralmedicalcard.repository.MedicineInsuranceRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInsurancesMapper {

    private final UserRepository userRepository;
    private final MedicineInsuranceRepository insuranceRepository;

    @Autowired
    public UserInsurancesMapper(UserRepository userRepository, MedicineInsuranceRepository insuranceRepository) {
        this.userRepository = userRepository;
        this.insuranceRepository = insuranceRepository;
    }

    public UserInsurances toEntity(UserInsurancesRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return UserInsurances.builder()
                .medicineInsurance(insuranceRepository.findById(request.getMedicineInsuranceId()).get())
                .user(userRepository.findById(request.getUserId()).get())
                .build();
    }

    public UserInsurancesResponse toResponse(UserInsurances entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return UserInsurancesResponse.builder()
                .id(entity.getId())
                .medicineInsuranceId(entity.getMedicineInsurance().getId())
                .userId(entity.getUser().getId())
                .dateActive(entity.getDateActive())
                .dateExpire(entity.getDateExpire())
                .active(entity.isActive())
                .build();
    }
}


