package com.example.kyrgyzstancentralmedicalcard.services;


import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    List<Diagnosis> getAllDiagnosis();

    Diagnosis createDiagnosis(Diagnosis diagnosis, Long id);

    Diagnosis getById(Long id);

    List<Diagnosis> getByName(String name);

    Diagnosis updateDiagnosis(Diagnosis diagnosis, Long id);

    List<Diagnosis> getAllDiagnosesByUserId(Long userId); // Добавленный метод
}
