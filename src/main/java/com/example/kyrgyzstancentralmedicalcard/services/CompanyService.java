package com.example.kyrgyzstancentralmedicalcard.services;

import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.entity.User;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company createCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyByName(String name);
    Company getById(Long id);
    List<User> getEmployees(Long companyId);
    Company updateCompany(Company company);
    String deleteCompanyById(Long id);
}
