package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, Long> {
    Optional<List<OrganizationUser>> findAllByOrganizationIdAndIsActiveTrue(Long organizationId);
    boolean existsByOrganizationIdAndUserId(Long organizationId, Long userId);
    Optional<OrganizationUser> findFirstByUserIdAndIsActiveTrue(Long userId);
}
