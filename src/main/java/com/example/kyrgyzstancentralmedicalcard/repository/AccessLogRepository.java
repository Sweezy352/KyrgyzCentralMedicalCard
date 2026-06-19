package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
    List<AccessLog> findAllByOrganizationIdOrderByAccessedAtDesc(Long organizationId);
    List<AccessLog> findAllByPatientIdOrderByAccessedAtDesc(Long patientId);
}
