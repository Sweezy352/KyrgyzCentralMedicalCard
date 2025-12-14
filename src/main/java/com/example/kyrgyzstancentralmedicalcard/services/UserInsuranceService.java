package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;

import java.util.List;

public interface UserInsuranceService {
    UserInsurances addInsuranceToUser(Long medicineInsuranceId);
    UserInsurances getById(Long id);
    UserInsurances getByUserId();
}
