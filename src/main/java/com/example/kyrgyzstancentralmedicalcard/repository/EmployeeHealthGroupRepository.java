package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.EmployeeHealthGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeHealthGroupRepository extends JpaRepository<EmployeeHealthGroup, Long> {
    Optional<List<EmployeeHealthGroup>> findAllByOrganizationIdAndIsActiveTrue(Long organizationId);
    long countByOrganizationIdAndIsActiveTrue(Long organizationId);
    long countByOrganizationIdAndIsActiveTrueAndNextCheckupDateBefore(Long organizationId, LocalDate date);

    @Query("SELECT ehg.healthGroup, COUNT(ehg) FROM EmployeeHealthGroup ehg WHERE ehg.organization.id = :orgId AND ehg.isActive = true GROUP BY ehg.healthGroup")
    List<Object[]> countByHealthGroup(@Param("orgId") Long orgId);

    @Query(value = "SELECT ehg.department, COUNT(v.id) FROM employee_health_groups ehg JOIN medical_visits v ON v.patient_id = ehg.patient_id WHERE ehg.organization_id = :orgId AND ehg.is_active = true GROUP BY ehg.department", nativeQuery = true)
    List<Object[]> findSickDaysByDepartment(@Param("orgId") Long orgId);
}
