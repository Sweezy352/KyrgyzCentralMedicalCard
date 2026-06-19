package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.MedicalVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Long> {
    Optional<List<MedicalVisit>> findAllByPatientId(Long patientId);
    Optional<List<MedicalVisit>> findAllByClinicId(Long clinicId);

    @Query("SELECT COUNT(DISTINCT v.patient.id) FROM MedicalVisit v WHERE v.clinic.id = :clinicId AND v.visitDate BETWEEN :from AND :to")
    long countDistinctPatientsByClinic(@Param("clinicId") Long clinicId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT COUNT(v) FROM MedicalVisit v WHERE v.clinic.id = :clinicId AND v.visitDate BETWEEN :from AND :to")
    long countVisitsByClinic(@Param("clinicId") Long clinicId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT v.diagnosisPrimary, COUNT(v) FROM MedicalVisit v WHERE v.clinic.id = :clinicId AND v.visitDate BETWEEN :from AND :to GROUP BY v.diagnosisPrimary ORDER BY COUNT(v) DESC")
    List<Object[]> findTopDiagnosesByClinic(@Param("clinicId") Long clinicId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query(value = "SELECT TO_CHAR(visit_date, 'YYYY-MM') as month, COUNT(*) FROM medical_visits WHERE clinic_id = :clinicId AND visit_date BETWEEN :from AND :to GROUP BY month ORDER BY month", nativeQuery = true)
    List<Object[]> findVisitDynamicsByClinic(@Param("clinicId") Long clinicId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT ou.user.fio, COUNT(v) FROM MedicalVisit v JOIN v.doctor ou WHERE v.clinic.id = :clinicId AND v.visitDate BETWEEN :from AND :to GROUP BY ou.user.fio ORDER BY COUNT(v) DESC")
    List<Object[]> findDoctorLoadByClinic(@Param("clinicId") Long clinicId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query(value = "SELECT TO_CHAR(v.visit_date, 'YYYY-MM') as month, COUNT(*) FROM medical_visits v JOIN employee_health_groups ehg ON ehg.patient_id = v.patient_id WHERE ehg.organization_id = :orgId AND v.visit_date BETWEEN :from AND :to GROUP BY month ORDER BY month", nativeQuery = true)
    List<Object[]> findVisitDynamicsByEmployer(@Param("orgId") Long orgId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT v.diagnosisPrimary, COUNT(v) FROM MedicalVisit v JOIN EmployeeHealthGroup ehg ON ehg.patient.id = v.patient.id WHERE ehg.organization.id = :orgId AND v.visitDate BETWEEN :from AND :to GROUP BY v.diagnosisPrimary ORDER BY COUNT(v) DESC")
    List<Object[]> findTopDiagnosesByEmployer(@Param("orgId") Long orgId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
