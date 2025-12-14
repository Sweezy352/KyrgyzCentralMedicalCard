package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.entity.UserInsurances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInsurancesRepository extends JpaRepository<UserInsurances, Long> {
    List<UserInsurances> findAllByUser(User user); // Добавленный метод
}
