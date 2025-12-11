package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.CompanyRepository;
import com.example.kyrgyzstancentralmedicalcard.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name).orElseThrow(() -> new RuntimeException("Такая клиника не найдена"));
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Такая клиника не найдена"));
    }

    @Override
    public List<User> getEmployees(Long companyId) {
        Company company = getById(companyId);
        return company.getEmployees();
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public String deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
        return "Успешно удалено";
    }
}
