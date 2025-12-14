package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.MedicineInsuranceRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.MedicineInsuranceResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.MedicineInsurance;
import com.example.kyrgyzstancentralmedicalcard.mapper.MedicineInsuranceMapper;
import com.example.kyrgyzstancentralmedicalcard.services.MedicineInsuranceService;
import com.example.kyrgyzstancentralmedicalcard.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine-insurance")
@RequiredArgsConstructor
public class MedicineInsuranceController {
    private final MedicineInsuranceService medicineInsuranceService;
    private final MedicineInsuranceMapper medicineInsuranceMapper;
    private final UserService userService;

    @PostMapping("/create-medicine-insurance")
    public ResponseEntity<MedicineInsuranceResponse> createMedicineInsurance(@RequestBody MedicineInsuranceRequest medicineInsuranceRequest){
        return ResponseEntity.ok(medicineInsuranceMapper.toResponse(medicineInsuranceService.createMedicineInsurance(medicineInsuranceMapper.toEntity(medicineInsuranceRequest))));
    }

    @GetMapping("/get-all-medicine-insurance")
    public ResponseEntity<List<MedicineInsuranceResponse>> getAllMedicineInsurances(){
        return ResponseEntity.ok(medicineInsuranceService.getAllMedicineInsurances().stream().map(medicineInsuranceMapper::toResponse).toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MedicineInsuranceResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(medicineInsuranceMapper.toResponse(medicineInsuranceService.getById(id)));
    }

    @PutMapping("/update-medicine-insurance")
    public ResponseEntity<MedicineInsuranceResponse> updateMedicineInsurance(MedicineInsuranceRequest medicineInsuranceRequest){
        return ResponseEntity.ok(medicineInsuranceMapper.toResponse(medicineInsuranceService.updateMedicineInsurance(medicineInsuranceMapper.toEntity(medicineInsuranceRequest))));
    }
}
