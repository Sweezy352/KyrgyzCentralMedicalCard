package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationUser;

import java.util.List;

public interface OrganizationService {
    Organization create(Organization organization);
    Organization getById(Long id);
    Organization update(Long id, Organization organization);
    List<OrganizationUser> getStaff(Long organizationId);
    OrganizationUser addStaff(Long organizationId, OrganizationUser organizationUser);
    void removeStaff(Long organizationId, Long userId);
}
