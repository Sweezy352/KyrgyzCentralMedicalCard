package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.PatientConsent;

import java.util.List;

public interface ConsentService {
    List<PatientConsent> getByPatient(Long patientId);
    PatientConsent grant(Long patientId, PatientConsent consent);
    void revoke(Long patientId, Long consentId);
    boolean hasActiveConsent(Long patientId, Long organizationId);
}
