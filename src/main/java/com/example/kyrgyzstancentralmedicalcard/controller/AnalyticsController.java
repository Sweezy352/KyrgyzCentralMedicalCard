package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.response.ClinicDashboardResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.EmployerDashboardResponse;
import com.example.kyrgyzstancentralmedicalcard.services.AnalyticsService;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.repository.OrganizationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final AuthService authService;
    private final OrganizationUserRepository organizationUserRepository;

    @GetMapping("/clinic/my/dashboard")
    public ResponseEntity<ClinicDashboardResponse> myClinicDashboard(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        Long userId = authService.getCurrentUser().getId();
        log.info("[clinic/my] userId={}", userId);
        var ou = organizationUserRepository.findFirstByUserIdAndIsActiveTrue(userId);
        log.info("[clinic/my] organizationUser found={}", ou.isPresent());
        Long orgId = ou.orElseThrow(() -> new RuntimeException("Вы не привязаны ни к одной организации"))
                .getOrganization().getId();
        log.info("[clinic/my] orgId={}", orgId);
        LocalDateTime dateTo = to != null ? to : LocalDateTime.now();
        LocalDateTime dateFrom = from != null ? from : dateTo.minusMonths(3);
        return ResponseEntity.ok(analyticsService.getClinicDashboard(orgId, dateFrom, dateTo));
    }

    @GetMapping("/employer/my/dashboard")
    public ResponseEntity<EmployerDashboardResponse> myEmployerDashboard(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        Long userId = authService.getCurrentUser().getId();
        log.info("[employer/my] userId={}", userId);
        var ou = organizationUserRepository.findFirstByUserIdAndIsActiveTrue(userId);
        log.info("[employer/my] organizationUser found={}", ou.isPresent());
        Long orgId = ou.orElseThrow(() -> new RuntimeException("Вы не привязаны ни к одной организации"))
                .getOrganization().getId();
        log.info("[employer/my] orgId={}", orgId);
        LocalDateTime dateTo = to != null ? to : LocalDateTime.now();
        LocalDateTime dateFrom = from != null ? from : dateTo.minusMonths(3);
        return ResponseEntity.ok(analyticsService.getEmployerDashboard(orgId, dateFrom, dateTo));
    }

    @GetMapping("/clinic/{id}/dashboard")
    public ResponseEntity<ClinicDashboardResponse> clinicDashboard(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(analyticsService.getClinicDashboard(id, from, to));
    }

    @GetMapping("/employer/{id}/dashboard")
    public ResponseEntity<EmployerDashboardResponse> employerDashboard(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(analyticsService.getEmployerDashboard(id, from, to));
    }
}
