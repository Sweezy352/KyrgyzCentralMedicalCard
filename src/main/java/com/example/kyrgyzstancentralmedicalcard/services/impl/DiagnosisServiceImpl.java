package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.DiagnosisRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final AuthService authService;

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }

    @Override
    public Diagnosis createDiagnosis(Diagnosis diagnosis) {
        User userDoc = authService.getCurrentUser();
        diagnosis.setUserDoc(userDoc);
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public Diagnosis getById(Long id) {
        return diagnosisRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой диагноза не существует"));
    }

    @Override
    public List<Diagnosis> getByName(String name) {
        return diagnosisRepository.findByName(name).orElseThrow(() -> new RuntimeException("Такой диагноза у вас не найден"));
    }

    @Override
    public Diagnosis updateDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }
}
