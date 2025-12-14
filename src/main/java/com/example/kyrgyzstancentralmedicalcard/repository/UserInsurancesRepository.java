package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInsurancesRepository extends JpaRepository<UserInsurances, Long> {
    Optional<UserInsurances> findByUserId(Long userId);
    Boolean existsByUserId(Long userId);
}
