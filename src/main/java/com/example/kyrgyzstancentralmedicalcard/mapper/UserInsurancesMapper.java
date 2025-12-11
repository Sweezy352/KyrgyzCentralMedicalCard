package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserInsurancesRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserInsurancesResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import com.example.kyrgyzstancentralmedicalcard.repository.MedicineInsuranceRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserInsurancesMapper extends BaseMapper<UserInsurances, UserInsurancesRequest, UserInsurancesResponse> {

    private final UserRepository userRepository;
    private final MedicineInsuranceRepository insuranceRepository;
    private final MedicineInsuranceMapper insuranceMapper;

    public UserInsurancesMapper(UserRepository userRepository, MedicineInsuranceRepository insuranceRepository, MedicineInsuranceMapper insuranceMapper) {
        this.userRepository = userRepository;
        this.insuranceRepository = insuranceRepository;
        this.insuranceMapper = insuranceMapper;
    }

    @Override
    public UserInsurances toEntity(UserInsurancesRequest request) {
        UserInsurances entity = new UserInsurances();
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
            entity.setUser(user);
        }
        if (request.getMedicineInsuranceId() != null) {
            MedicineInsurance insurance = insuranceRepository.findById(request.getMedicineInsuranceId())
                    .orElseThrow(() -> new RuntimeException("MedicineInsurance not found with ID: " + request.getMedicineInsuranceId()));
            entity.setMedicineInsurance(insurance);
        }
        return entity;
    }

    @Override
    public UserInsurancesResponse toResponse(UserInsurances entity) {
        UserInsurancesResponse response = mapFields(entity, new UserInsurancesResponse());
        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getId());
        }
        if (entity.getMedicineInsurance() != null) {
            response.setMedicineInsurance(insuranceMapper.toResponse(entity.getMedicineInsurance()));
        }
        return response;
    }
}


