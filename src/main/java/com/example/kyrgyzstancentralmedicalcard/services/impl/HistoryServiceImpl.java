package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.entity.History;
import com.example.kyrgyzstancentralmedicalcard.repository.CompanyRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.HistoryRepository;
import com.example.kyrgyzstancentralmedicalcard.services.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<History> getAllHistoriesByUserId(Long userId) {
        return historyRepository.findAllByUserId(userId).orElseThrow(() -> new RuntimeException("Такой истории не существует"));
    }

    @Override
    public History createHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public History getById(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new RuntimeException("Такой истории не существует"));
    }

    @Override
    public List<History> getByDate(LocalDate date) {
        return historyRepository.findByDateCreated(date).orElseThrow(() -> new RuntimeException("Такой истории не существует"));
    }

    @Override
    public List<History> getByName(String name) {
        return historyRepository.findByName(name).orElseThrow(() -> new RuntimeException("Такая история не найдена"));
    }

    @Override
    public List<History> getByCompany(String companyName) {
        Company company = companyRepository.findByCompanyName(companyName).orElseThrow(() -> new RuntimeException("Такой компании не существует"));

        return null;

    }

    @Override
    public List<History> getByFIO(String fio) {
        return null;
    }
}
