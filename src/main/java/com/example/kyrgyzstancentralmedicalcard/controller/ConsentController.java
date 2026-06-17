package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.ConsentRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ConsentResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.PatientConsent;
import com.example.kyrgyzstancentralmedicalcard.mapper.PatientConsentMapper;
import com.example.kyrgyzstancentralmedicalcard.services.ConsentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients/{patientId}/consents")
@RequiredArgsConstructor
public class ConsentController {

    private final ConsentService consentService;
    private final PatientConsentMapper patientConsentMapper;

    @GetMapping
    public ResponseEntity<List<ConsentResponse>> getConsents(@PathVariable Long patientId) {
        return ResponseEntity.ok(consentService.getByPatient(patientId).stream().map(patientConsentMapper::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<ConsentResponse> grant(@PathVariable Long patientId, @Valid @RequestBody ConsentRequest request) {
        PatientConsent consent = PatientConsent.builder()
                .organization(Organization.builder().id(request.organizationId()).build())
                .consentType(request.consentType())
                .expiresAt(request.expiresAt())
                .build();
        return ResponseEntity.ok(patientConsentMapper.toDto(consentService.grant(patientId, consent)));
    }

    @DeleteMapping("/{consentId}")
    public ResponseEntity<Void> revoke(@PathVariable Long patientId, @PathVariable Long consentId) {
        consentService.revoke(patientId, consentId);
        return ResponseEntity.noContent().build();
    }
}
