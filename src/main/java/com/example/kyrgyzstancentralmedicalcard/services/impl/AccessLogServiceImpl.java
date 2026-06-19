package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.AccessLog;
import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.AccessLogRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;

    @Override
    public void log(Long patientId, Long accessedByUserId, Long organizationId, String accessType, String ipAddress) {
        AccessLog log = AccessLog.builder()
                .patient(User.builder().id(patientId).build())
                .accessedByUser(User.builder().id(accessedByUserId).build())
                .organization(Organization.builder().id(organizationId).build())
                .accessType(accessType)
                .ipAddress(ipAddress)
                .build();
        accessLogRepository.save(log);
    }

    @Override
    public AccessLog record(AccessLog accessLog) {
        AccessLog saved = accessLogRepository.save(accessLog);
        // перезагружаем для маппера (patient, accessedByUser, organization)
        return accessLogRepository.findById(saved.getId()).orElse(saved);
    }

    @Override
    public List<AccessLog> getByOrganization(Long organizationId) {
        return accessLogRepository.findAllByOrganizationIdOrderByAccessedAtDesc(organizationId);
    }

    @Override
    public List<AccessLog> getByPatient(Long patientId) {
        return accessLogRepository.findAllByPatientIdOrderByAccessedAtDesc(patientId);
    }
}
