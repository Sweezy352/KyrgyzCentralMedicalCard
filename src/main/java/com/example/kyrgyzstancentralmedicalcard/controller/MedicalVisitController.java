package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.MedicalVisitRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.MedicalVisitResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.MedicalVisit;
import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationUser;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.mapper.MedicalVisitMapper;
import com.example.kyrgyzstancentralmedicalcard.services.MedicalVisitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedicalVisitController {

    private final MedicalVisitService medicalVisitService;
    private final MedicalVisitMapper medicalVisitMapper;

    @PostMapping("/api/visits")
    public ResponseEntity<MedicalVisitResponse> create(@Valid @RequestBody MedicalVisitRequest request) {
        MedicalVisit visit = MedicalVisit.builder()
                .patient(User.builder().id(request.patientId()).build())
                .clinic(Organization.builder().id(request.clinicId()).build())
                .doctor(OrganizationUser.builder().id(request.doctorId()).build())
                .visitDate(request.visitDate())
                .visitType(request.visitType())
                .chiefComplaint(request.chiefComplaint())
                .diagnosisPrimary(request.diagnosisPrimary())
                .diagnosisSecondary(request.diagnosisSecondary())
                .treatmentPlan(request.treatmentPlan())
                .notes(request.notes())
                .durationMinutes(request.durationMinutes())
                .cost(request.cost())
                .build();
        return ResponseEntity.ok(medicalVisitMapper.toDto(medicalVisitService.create(visit)));
    }

    @GetMapping("/api/visits/{id}")
    public ResponseEntity<MedicalVisitResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalVisitMapper.toDto(medicalVisitService.getById(id)));
    }

    @GetMapping("/api/patients/{id}/visits")
    public ResponseEntity<List<MedicalVisitResponse>> getByPatient(@PathVariable Long id) {
        return ResponseEntity.ok(medicalVisitService.getByPatient(id).stream().map(medicalVisitMapper::toDto).toList());
    }

    @GetMapping("/api/organizations/{id}/visits")
    public ResponseEntity<List<MedicalVisitResponse>> getByOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(medicalVisitService.getByClinic(id).stream().map(medicalVisitMapper::toDto).toList());
    }
}
