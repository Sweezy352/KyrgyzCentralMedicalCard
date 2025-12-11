package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<List<History>> findAllByUserId(Long userId);

    Optional<List<History>> findByDateCreated(LocalDate dateCreated);

    Optional<List<History>> findByUserDocFio(String fio);

    Optional<List<History>> findByName(String name);
}
