package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.dto.response.ClinicDashboardResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.EmployerDashboardResponse;
import com.example.kyrgyzstancentralmedicalcard.repository.EmployeeHealthGroupRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.MedicalVisitRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final MedicalVisitRepository visitRepository;
    private final EmployeeHealthGroupRepository healthGroupRepository;

    @Override
    public ClinicDashboardResponse getClinicDashboard(Long clinicId, LocalDateTime from, LocalDateTime to) {
        long totalPatients = visitRepository.countDistinctPatientsByClinic(clinicId, from, to);
        long totalVisits = visitRepository.countVisitsByClinic(clinicId, from, to);
        long days = ChronoUnit.DAYS.between(from, to) + 1;
        double avgPerDay = days > 0 ? (double) totalVisits / days : 0;

        List<ClinicDashboardResponse.DiagnosisStatResponse> topDiagnoses = visitRepository
                .findTopDiagnosesByClinic(clinicId, from, to)
                .stream()
                .limit(10)
                .map(row -> new ClinicDashboardResponse.DiagnosisStatResponse(
                        row[0] != null ? row[0].toString() : "N/A",
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());

        List<ClinicDashboardResponse.MonthlyStatResponse> visitDynamics = visitRepository
                .findVisitDynamicsByClinic(clinicId, from, to)
                .stream()
                .map(row -> new ClinicDashboardResponse.MonthlyStatResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());

        List<ClinicDashboardResponse.DoctorLoadResponse> doctorLoad = visitRepository
                .findDoctorLoadByClinic(clinicId, from, to)
                .stream()
                .map(row -> new ClinicDashboardResponse.DoctorLoadResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());

        return new ClinicDashboardResponse(
                from.toLocalDate(),
                to.toLocalDate(),
                totalPatients,
                totalVisits,
                Math.round(avgPerDay * 10.0) / 10.0,
                topDiagnoses,
                visitDynamics,
                doctorLoad);
    }

    @Override
    public EmployerDashboardResponse getEmployerDashboard(Long orgId, LocalDateTime from, LocalDateTime to) {
        long totalEmployees = healthGroupRepository.countByOrganizationIdAndIsActiveTrue(orgId);

        Map<String, Long> healthGroups = new HashMap<>();
        healthGroupRepository.countByHealthGroup(orgId)
                .forEach(row -> healthGroups.put(row[0].toString(), ((Number) row[1]).longValue()));

        List<EmployerDashboardResponse.DepartmentSickDaysResponse> sickDays = healthGroupRepository
                .findSickDaysByDepartment(orgId)
                .stream()
                .map(row -> new EmployerDashboardResponse.DepartmentSickDaysResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());

        long overdueCheckups = healthGroupRepository
                .countByOrganizationIdAndIsActiveTrueAndNextCheckupDateBefore(orgId, LocalDate.now());

        List<EmployerDashboardResponse.DiagnosisStatResponse> topDiagnoses = visitRepository
                .findTopDiagnosesByEmployer(orgId, from, to)
                .stream()
                .limit(10)
                .map(row -> new EmployerDashboardResponse.DiagnosisStatResponse(
                        row[0] != null ? row[0].toString() : "N/A",
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());

        return new EmployerDashboardResponse(
                totalEmployees,
                healthGroups,
                sickDays,
                overdueCheckups,
                topDiagnoses);
    }
}
