package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.PatientConsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientConsentRepository extends JpaRepository<PatientConsent, Long> {
    Optional<List<PatientConsent>> findAllByPatientId(Long patientId);

    @Query("SELECT COUNT(c) > 0 FROM PatientConsent c WHERE c.patient.id = :patientId " +
           "AND c.organization.id = :orgId AND c.revokedAt IS NULL " +
           "AND (c.expiresAt IS NULL OR c.expiresAt > CURRENT_TIMESTAMP)")
    boolean existsActiveConsent(@Param("patientId") Long patientId, @Param("orgId") Long orgId);
}
