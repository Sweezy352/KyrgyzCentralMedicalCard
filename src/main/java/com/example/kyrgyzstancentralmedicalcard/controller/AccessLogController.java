package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.AccessLogRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.AccessLogResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.AccessLog;
import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.mapper.AccessLogMapper;
import com.example.kyrgyzstancentralmedicalcard.repository.OrganizationUserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AccessLogService;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccessLogController {

    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final AuthService authService;
    private final OrganizationUserRepository organizationUserRepository;

    @PostMapping("/api/access-logs")
    public ResponseEntity<AccessLogResponse> create(@Valid @RequestBody AccessLogRequest request,
                                                    HttpServletRequest httpRequest) {
        User current = authService.getCurrentUser();
        Long organizationId = resolveOrganizationId(request.organizationId(), current);
        AccessLog log = AccessLog.builder()
                .patient(User.builder().id(request.patientId()).build())
                .accessedByUser(User.builder().id(current.getId()).build())
                .organization(organizationId != null ? Organization.builder().id(organizationId).build() : null)
                .accessType(request.accessType())
                .ipAddress(httpRequest.getRemoteAddr())
                .build();
        return ResponseEntity.ok(accessLogMapper.toDto(accessLogService.record(log)));
    }

    @GetMapping("/api/organizations/{id}/access-logs")
    public ResponseEntity<List<AccessLogResponse>> getByOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(accessLogService.getByOrganization(id)
                .stream().map(accessLogMapper::toDto).toList());
    }

    @GetMapping("/api/patients/{id}/access-logs")
    public ResponseEntity<List<AccessLogResponse>> getByPatient(@PathVariable Long id) {
        return ResponseEntity.ok(accessLogService.getByPatient(id)
                .stream().map(accessLogMapper::toDto).toList());
    }

    // Если организация не передана — берём активную организацию текущего пользователя (может быть null для ADMIN)
    private Long resolveOrganizationId(Long provided, User current) {
        if (provided != null) {
            return provided;
        }
        return organizationUserRepository.findFirstByUserIdAndIsActiveTrue(current.getId())
                .map(ou -> ou.getOrganization().getId())
                .orElse(null);
    }
}
