package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.EmployeeHealthGroupRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.EmployeeHealthGroupResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.EmployeeHealthGroup;
import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.mapper.EmployeeHealthGroupMapper;
import com.example.kyrgyzstancentralmedicalcard.repository.OrganizationUserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import com.example.kyrgyzstancentralmedicalcard.services.EmployeeHealthGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeHealthGroupController {

    private final EmployeeHealthGroupService employeeHealthGroupService;
    private final EmployeeHealthGroupMapper employeeHealthGroupMapper;
    private final AuthService authService;
    private final OrganizationUserRepository organizationUserRepository;

    @PostMapping("/api/health-groups")
    public ResponseEntity<EmployeeHealthGroupResponse> create(@Valid @RequestBody EmployeeHealthGroupRequest request) {
        Long organizationId = resolveOrganizationId(request.organizationId());
        EmployeeHealthGroup healthGroup = EmployeeHealthGroup.builder()
                .organization(Organization.builder().id(organizationId).build())
                .patient(User.builder().id(request.patientId()).build())
                .employeeId(request.employeeId())
                .department(request.department())
                .position(request.position())
                .healthGroup(request.healthGroup())
                .lastCheckupDate(request.lastCheckupDate())
                .nextCheckupDate(request.nextCheckupDate())
                .isActive(true)
                .joinedAt(request.joinedAt())
                .build();
        return ResponseEntity.ok(employeeHealthGroupMapper.toDto(employeeHealthGroupService.create(healthGroup)));
    }

    @GetMapping("/api/organizations/{id}/health-groups")
    public ResponseEntity<List<EmployeeHealthGroupResponse>> getByOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(employeeHealthGroupService.getByOrganization(id)
                .stream().map(employeeHealthGroupMapper::toDto).toList());
    }

    @GetMapping("/api/patients/{id}/health-groups")
    public ResponseEntity<List<EmployeeHealthGroupResponse>> getByPatient(@PathVariable Long id) {
        return ResponseEntity.ok(employeeHealthGroupService.getByPatient(id)
                .stream().map(employeeHealthGroupMapper::toDto).toList());
    }

    private Long resolveOrganizationId(Long provided) {
        if (provided != null) {
            return provided;
        }
        User current = authService.getCurrentUser();
        return organizationUserRepository.findFirstByUserIdAndIsActiveTrue(current.getId())
                .map(ou -> ou.getOrganization().getId())
                .orElseThrow(() -> new RuntimeException("Текущий пользователь не привязан к организации"));
    }
}
