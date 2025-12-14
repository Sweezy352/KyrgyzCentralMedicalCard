package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;
import com.example.kyrgyzstancentralmedicalcard.entity.User; // Импортируем User
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Optional<List<Diagnosis>> findByName(String name);
    List<Diagnosis> findAllByUser(User user); // Добавленный метод
}
