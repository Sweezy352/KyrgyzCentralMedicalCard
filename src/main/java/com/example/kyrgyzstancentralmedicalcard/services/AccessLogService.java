package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.AccessLog;

import java.util.List;

public interface AccessLogService {
    void log(Long patientId, Long accessedByUserId, Long organizationId, String accessType, String ipAddress);
    AccessLog record(AccessLog accessLog);
    List<AccessLog> getByOrganization(Long organizationId);
    List<AccessLog> getByPatient(Long patientId);
}
