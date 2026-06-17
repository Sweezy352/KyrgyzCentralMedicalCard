package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.OrganizationRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.request.OrganizationUserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.OrganizationResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.OrganizationUserResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationUser;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.mapper.OrganizationMapper;
import com.example.kyrgyzstancentralmedicalcard.mapper.OrganizationUserMapper;
import com.example.kyrgyzstancentralmedicalcard.services.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    private final OrganizationUserMapper organizationUserMapper;

    @PostMapping
    public ResponseEntity<OrganizationResponse> create(@Valid @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationMapper.toDto(organizationService.create(organizationMapper.toEntity(request))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationMapper.toDto(organizationService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponse> update(@PathVariable Long id, @Valid @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationMapper.toDto(organizationService.update(id, organizationMapper.toEntity(request))));
    }

    @GetMapping("/{id}/staff")
    public ResponseEntity<List<OrganizationUserResponse>> getStaff(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getStaff(id).stream().map(organizationUserMapper::toDto).toList());
    }

    @PostMapping("/{id}/staff")
    public ResponseEntity<OrganizationUserResponse> addStaff(@PathVariable Long id, @Valid @RequestBody OrganizationUserRequest request) {
        OrganizationUser ou = OrganizationUser.builder()
                .user(User.builder().id(request.userId()).build())
                .role(request.role())
                .department(request.department())
                .build();
        return ResponseEntity.ok(organizationUserMapper.toDto(organizationService.addStaff(id, ou)));
    }

    @DeleteMapping("/{id}/staff/{uid}")
    public ResponseEntity<Void> removeStaff(@PathVariable Long id, @PathVariable Long uid) {
        organizationService.removeStaff(id, uid);
        return ResponseEntity.noContent().build();
    }
}
