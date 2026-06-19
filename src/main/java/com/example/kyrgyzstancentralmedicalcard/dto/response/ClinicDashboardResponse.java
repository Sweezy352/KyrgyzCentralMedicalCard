package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ClinicDashboardResponse(
        LocalDate periodFrom,
        LocalDate periodTo,
        long totalPatients,
        long totalVisits,
        double avgVisitsPerDay,
        List<DiagnosisStatResponse> topDiagnoses,
        List<MonthlyStatResponse> visitDynamics,
        List<DoctorLoadResponse> doctorLoad
) {
    public record DiagnosisStatResponse(String code, long count) {}
    public record MonthlyStatResponse(String month, long count) {}
    public record DoctorLoadResponse(String doctorName, long visits) {}
}
