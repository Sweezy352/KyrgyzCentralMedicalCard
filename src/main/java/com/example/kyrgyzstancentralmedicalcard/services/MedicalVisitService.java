package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicalVisit;

import java.util.List;

public interface MedicalVisitService {
    MedicalVisit create(MedicalVisit visit);
    MedicalVisit getById(Long id);
    List<MedicalVisit> getByPatient(Long patientId);
    List<MedicalVisit> getByClinic(Long clinicId);
}
