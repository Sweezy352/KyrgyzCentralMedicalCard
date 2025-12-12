package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.AllergieEntity;

import java.time.LocalDate;
import java.util.List;

public interface AllergieService {
    AllergieEntity addAllergie(Long userId, AllergieEntity allergieEntity);
    AllergieEntity getAllergieById(Long id);
    List<AllergieEntity> getAll();
    List<AllergieEntity> getAllByName(String name);
    List<AllergieEntity> getAllByDate(LocalDate date);
    List<AllergieEntity> getAllByDoctor(String doctorName);
}
