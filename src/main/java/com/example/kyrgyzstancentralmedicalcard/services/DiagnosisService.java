package com.example.kyrgyzstancentralmedicalcard.services;


import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    List<Diagnosis> getAllDiagnosis();

    Diagnosis createDiagnosis(Diagnosis diagnosis);

    Diagnosis getById(Long id);

    List<Diagnosis> getByName(String name);

    Diagnosis updateDiagnosis(Diagnosis diagnosis);
}
