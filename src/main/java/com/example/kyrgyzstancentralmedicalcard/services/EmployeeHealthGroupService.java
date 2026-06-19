package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.EmployeeHealthGroup;

import java.util.List;

public interface EmployeeHealthGroupService {
    EmployeeHealthGroup create(EmployeeHealthGroup healthGroup);
    List<EmployeeHealthGroup> getByOrganization(Long organizationId);
    List<EmployeeHealthGroup> getByPatient(Long patientId);
}
