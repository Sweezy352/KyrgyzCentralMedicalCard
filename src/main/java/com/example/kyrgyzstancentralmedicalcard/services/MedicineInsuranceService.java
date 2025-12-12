package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;

import java.util.List;

public interface MedicineInsuranceService {
    MedicineInsurance createMedicineInsurance(MedicineInsurance medicineInsurance);

    List<MedicineInsurance> getAllMedicineInsurances();

    MedicineInsurance getById(Long id);

    MedicineInsurance updateMedicineInsurance(MedicineInsurance medicineInsurance);


}
