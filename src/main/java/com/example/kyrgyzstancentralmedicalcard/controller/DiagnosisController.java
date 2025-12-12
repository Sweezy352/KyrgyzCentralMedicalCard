package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.DiagnosisRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.DiagnosisResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;
import com.example.kyrgyzstancentralmedicalcard.mapper.DiagnosisMapper;
import com.example.kyrgyzstancentralmedicalcard.services.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService diagnosisService;
    private final DiagnosisMapper diagnosisMapper;

    @PostMapping("/create-diagnosis/{id}")
    public ResponseEntity<DiagnosisResponse> createDiagnosis(@RequestBody DiagnosisRequest diagnosisRequest, @PathVariable("id") Long userId){
        return ResponseEntity.ok(diagnosisMapper.toDto(diagnosisService.createDiagnosis(diagnosisMapper.toEntity(diagnosisRequest), userId)));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<DiagnosisResponse> getDiagnosisById(@PathVariable("id") Long id){
        return ResponseEntity.ok(diagnosisMapper.toDto(diagnosisService.getById(id)));
    }

    @GetMapping("get-by-name")
    public ResponseEntity<List<DiagnosisResponse>> getDiagnosisByName(@RequestParam String name){
        return ResponseEntity.ok(diagnosisService.getByName(name).stream().map(diagnosisMapper::toDto).toList());
    }

    @GetMapping("/get-all-diagnosis")
    public ResponseEntity<List<DiagnosisResponse>> getAllDiagnosis(){
        return ResponseEntity.ok(diagnosisService.getAllDiagnosis().stream().map(diagnosisMapper::toDto).toList());
    }

    @PutMapping("/update-diagnosis/{id}")
    public ResponseEntity<DiagnosisResponse> updateDiagnosis(@PathVariable Long id, Diagnosis diagnosis){
        return ResponseEntity.ok(diagnosisMapper.toDto(diagnosisService.updateDiagnosis(diagnosis, id)));
    }
}
