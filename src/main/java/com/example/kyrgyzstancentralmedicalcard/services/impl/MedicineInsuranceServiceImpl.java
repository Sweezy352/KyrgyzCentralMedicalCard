package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.MedicineInsuranceRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.MedicineInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineInsuranceServiceImpl implements MedicineInsuranceService {
    private final MedicineInsuranceRepository medicineInsuranceRepository;
    private final AuthService authService;

    @Override
    public MedicineInsurance createMedicineInsurance(MedicineInsurance medicineInsurance) {
        return medicineInsuranceRepository.save(medicineInsurance);
    }

    @Override
    public List<MedicineInsurance> getAllMedicineInsurances() {
        return medicineInsuranceRepository.findAll();
    }

    @Override
    public MedicineInsurance getById(Long id) {
        return medicineInsuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой страховки не существует"));
    }

    @Override
    public MedicineInsurance updateMedicineInsurance(MedicineInsurance medicineInsurance) {
        return medicineInsuranceRepository.save(medicineInsurance);
    }
}
