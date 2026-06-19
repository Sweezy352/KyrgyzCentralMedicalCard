package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.EmployeeHealthGroup;
import com.example.kyrgyzstancentralmedicalcard.repository.EmployeeHealthGroupRepository;
import com.example.kyrgyzstancentralmedicalcard.services.EmployeeHealthGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeHealthGroupServiceImpl implements EmployeeHealthGroupService {

    private final EmployeeHealthGroupRepository employeeHealthGroupRepository;

    @Override
    public EmployeeHealthGroup create(EmployeeHealthGroup healthGroup) {
        EmployeeHealthGroup saved = employeeHealthGroupRepository.save(healthGroup);
        // перезагружаем, чтобы маппер увидел связанные сущности (organization, patient)
        return employeeHealthGroupRepository.findById(saved.getId()).orElse(saved);
    }

    @Override
    public List<EmployeeHealthGroup> getByOrganization(Long organizationId) {
        return employeeHealthGroupRepository.findAllByOrganizationIdAndIsActiveTrue(organizationId)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<EmployeeHealthGroup> getByPatient(Long patientId) {
        return employeeHealthGroupRepository.findAllByPatientId(patientId);
    }
}
