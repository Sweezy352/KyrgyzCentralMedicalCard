package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.response.UserInsurancesResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import com.example.kyrgyzstancentralmedicalcard.mapper.UserInsurancesMapper;
import com.example.kyrgyzstancentralmedicalcard.services.UserInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-insurance")
@RequiredArgsConstructor
public class UserInsuranceController {
    private final UserInsuranceService userInsuranceService;
    private final UserInsurancesMapper userInsurancesMapper;

    @PostMapping("/add-medicine-insurance/{id}")
    public ResponseEntity<UserInsurancesResponse> addUserInsurance(@PathVariable("id") Long medicineInsuranceId){
        return ResponseEntity.ok(userInsurancesMapper.toResponse(userInsuranceService.addInsuranceToUser(medicineInsuranceId)));
    }

    @GetMapping("/get-all-user-insurances")
    public ResponseEntity<List<UserInsurancesResponse>> getAllUserInsurances(){
        return ResponseEntity.ok(userInsuranceService.getAllUserInsurances().stream().map(userInsurancesMapper::toResponse).toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserInsurancesResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userInsurancesMapper.toResponse(userInsuranceService.getById(id)));
    }

}
