package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.util.List;
import java.util.Map;

public record EmployerDashboardResponse(
        long totalEmployees,
        Map<String, Long> healthGroups,
        List<DepartmentSickDaysResponse> sickDaysByDepartment,
        long overdueCheckups,
        List<DiagnosisStatResponse> topDiagnoses
) {
    public record DepartmentSickDaysResponse(String department, long sickDays) {}
    public record DiagnosisStatResponse(String name, long count) {}
}
