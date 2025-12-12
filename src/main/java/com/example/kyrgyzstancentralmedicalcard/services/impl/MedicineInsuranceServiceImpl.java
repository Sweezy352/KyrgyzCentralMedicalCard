package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import com.example.kyrgyzstancentralmedicalcard.services.MedicineInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineInsuranceServiceImpl implements MedicineInsuranceService {
    @Override
    public MedicineInsurance createMedicineInsurance(MedicineInsurance medicineInsurance) {
        return null;
    }

    @Override
    public List<MedicineInsurance> getAllMedicineInsurances() {
        return List.of();
    }

    @Override
    public MedicineInsurance getById(Long id) {
        return null;
    }

    @Override
    public MedicineInsurance updateMedicineInsurance(MedicineInsurance medicineInsurance) {
        return null;
    }
}
