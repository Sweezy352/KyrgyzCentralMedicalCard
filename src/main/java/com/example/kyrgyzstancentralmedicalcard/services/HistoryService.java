package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryService {
    List<History> getAllHistoriesByUserId(Long userId);
    History createHistory(History history);
    History getById(Long id);
    List<History> getByDate(LocalDate date);
    List<History> getByName(String name);
    List<History> getByCompany(String companyName);
    List<History> getByFIO(String fio);
}
