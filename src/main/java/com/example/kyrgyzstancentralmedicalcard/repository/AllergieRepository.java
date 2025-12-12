package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.AllergieEntity;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AllergieRepository extends JpaRepository<AllergieEntity, Long> {
    Optional<List<AllergieEntity>> findByNameAndUser(String name, User user);
    Optional<List<AllergieEntity>> findByDateCreatedAndUser(LocalDate date, User user);
    Optional<List<AllergieEntity>> findByDoctorAndUser(User doctor, User user);
}
