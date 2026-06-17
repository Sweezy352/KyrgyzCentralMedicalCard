package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.dto.response.ClinicDashboardResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.EmployerDashboardResponse;

import java.time.LocalDateTime;

public interface AnalyticsService {
    ClinicDashboardResponse getClinicDashboard(Long clinicId, LocalDateTime from, LocalDateTime to);
    EmployerDashboardResponse getEmployerDashboard(Long orgId, LocalDateTime from, LocalDateTime to);
}
