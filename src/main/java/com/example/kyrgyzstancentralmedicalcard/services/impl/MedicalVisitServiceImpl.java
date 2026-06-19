package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicalVisit;
import com.example.kyrgyzstancentralmedicalcard.repository.MedicalVisitRepository;
import com.example.kyrgyzstancentralmedicalcard.services.ConsentService;
import com.example.kyrgyzstancentralmedicalcard.services.MedicalVisitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalVisitServiceImpl implements MedicalVisitService {

    private final MedicalVisitRepository visitRepository;
    private final ConsentService consentService;

    @Override
    public MedicalVisit create(MedicalVisit visit) {
        Long patientId = visit.getPatient().getId();
        Long clinicId = visit.getClinic().getId();
        if (!consentService.hasActiveConsent(patientId, clinicId)) {
            throw new AccessDeniedException("Пациент не выдал согласие этой клинике");
        }
        return visitRepository.save(visit);
    }

    @Override
    public MedicalVisit getById(Long id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Визит не найден: " + id));
    }

    @Override
    public List<MedicalVisit> getByPatient(Long patientId) {
        return visitRepository.findAllByPatientId(patientId).orElse(List.of());
    }

    @Override
    public List<MedicalVisit> getByClinic(Long clinicId) {
        return visitRepository.findAllByClinicId(clinicId).orElse(List.of());
    }
}
