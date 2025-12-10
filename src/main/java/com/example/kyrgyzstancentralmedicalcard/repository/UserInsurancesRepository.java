package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInsurancesRepository extends JpaRepository<UserInsurances, Long> {
}
