package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import com.example.kyrgyzstancentralmedicalcard.repository.UserInsurancesRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.MedicineInsuranceService;
import com.example.kyrgyzstancentralmedicalcard.services.UserInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInsuranceServiceImpl implements UserInsuranceService {
    private final UserInsurancesRepository userInsurancesRepository;
    private final MedicineInsuranceService medicineInsuranceService;
    private final AuthService authService;

    @Override
    public UserInsurances addInsuranceToUser(Long medicineInsuranceId) {
        User currentUser = authService.getCurrentUser();
        MedicineInsurance medicineInsurance = medicineInsuranceService.getById(medicineInsuranceId);
        UserInsurances userInsurances = new UserInsurances();
        userInsurances.setUser(currentUser);
        userInsurances.setMedicineInsurance(medicineInsurance);
        return userInsurancesRepository.save(userInsurances);
    }

    @Override
    public List<UserInsurances> getAllUserInsurances() {
        User currentUser = authService.getCurrentUser();
        return currentUser.getUserInsurances();
    }

    @Override
    public UserInsurances getById(Long id) {
        return userInsurancesRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой страховки не существует"));
    }
}
