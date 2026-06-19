package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationUser;
import com.example.kyrgyzstancentralmedicalcard.repository.OrganizationRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.OrganizationUserRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationUserRepository organizationUserRepository;
    private final UserRepository userRepository;

    @Override
    public Organization create(Organization organization) {
        if (organizationRepository.existsByBin(organization.getBin())) {
            throw new IllegalArgumentException("Организация с таким БИН уже существует");
        }
        return organizationRepository.save(organization);
    }

    @Override
    public Organization getById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Организация не найдена: " + id));
    }

    @Override
    public Organization update(Long id, Organization updated) {
        Organization existing = getById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        existing.setContractStart(updated.getContractStart());
        existing.setContractEnd(updated.getContractEnd());
        existing.setIsActive(updated.getIsActive());
        return organizationRepository.save(existing);
    }

    @Override
    public List<OrganizationUser> getStaff(Long organizationId) {
        return organizationUserRepository.findAllByOrganizationIdAndIsActiveTrue(organizationId).orElse(List.of());
    }

    @Override
    public OrganizationUser addStaff(Long organizationId, OrganizationUser organizationUser) {
        Organization org = getById(organizationId);
        organizationUser.setOrganization(org);
        organizationUser.setUser(userRepository.findById(organizationUser.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + organizationUser.getUser().getId())));
        return organizationUserRepository.save(organizationUser);
    }

    @Override
    public void removeStaff(Long organizationId, Long userId) {
        organizationUserRepository.findAllByOrganizationIdAndIsActiveTrue(organizationId)
                .orElse(List.of())
                .stream()
                .filter(ou -> ou.getUser().getId().equals(userId))
                .findFirst()
                .ifPresent(ou -> {
                    ou.setIsActive(false);
                    organizationUserRepository.save(ou);
                });
    }
}
