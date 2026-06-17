package com.example.kyrgyzstancentralmedicalcard.services;

public interface AccessLogService {
    void log(Long patientId, Long accessedByUserId, Long organizationId, String accessType, String ipAddress);
}
