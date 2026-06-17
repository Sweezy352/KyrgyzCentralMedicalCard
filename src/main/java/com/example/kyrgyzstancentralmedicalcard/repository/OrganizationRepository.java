package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByBin(String bin);
    Optional<List<Organization>> findAllByType(OrganizationType type);
    boolean existsByBin(String bin);
}
