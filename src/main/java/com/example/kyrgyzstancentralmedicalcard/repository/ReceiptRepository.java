package com.example.kyrgyzstancentralmedicalcard.repository;

import com.example.kyrgyzstancentralmedicalcard.entity.Receipt;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<List<Receipt>> findByName(String name);
    Optional<List<Receipt>> findByDateCreatedAndUser(LocalDate dateCreated, User user);

    boolean existsByNumber(String number);
}
