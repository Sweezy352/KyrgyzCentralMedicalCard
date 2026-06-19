package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.PatientConsent;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.PatientConsentRepository;
import com.example.kyrgyzstancentralmedicalcard.services.ConsentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsentServiceImpl implements ConsentService {

    private final PatientConsentRepository consentRepository;

    @Override
    public List<PatientConsent> getByPatient(Long patientId) {
        return consentRepository.findAllByPatientId(patientId).orElse(List.of());
    }

    @Override
    public PatientConsent grant(Long patientId, PatientConsent consent) {
        consent.setPatient(User.builder().id(patientId).build());
        return consentRepository.save(consent);
    }

    @Override
    public void revoke(Long patientId, Long consentId) {
        PatientConsent consent = consentRepository.findById(consentId)
                .orElseThrow(() -> new EntityNotFoundException("Согласие не найдено: " + consentId));
        if (!consent.getPatient().getId().equals(patientId)) {
            throw new SecurityException("Нет прав на отзыв этого согласия");
        }
        consent.setRevokedAt(LocalDateTime.now());
        consentRepository.save(consent);
    }

    @Override
    public boolean hasActiveConsent(Long patientId, Long organizationId) {
        return consentRepository.existsActiveConsent(patientId, organizationId);
    }
}
