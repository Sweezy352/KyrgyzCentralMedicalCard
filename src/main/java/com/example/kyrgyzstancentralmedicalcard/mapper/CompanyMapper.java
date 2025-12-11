package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.CompanyRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.CompanyResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CompanyMapper {

    private final UserRepository userRepository;

    public CompanyMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Company toEntity(CompanyRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return Company.builder()
                .companyName(request.getCompanyName())
                .description(request.getDescription())
                .user(userRepository.findById(request.getUserId()).get())
                .build();
    }

    public CompanyResponse toDto(Company entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return CompanyResponse.builder()
                .id(entity.getId())
                .companyName(entity.getCompanyName())
                .description(entity.getDescription())
                .userId(entity.getUser().getId())
                .dateCreated(entity.getDateCreated())
                .build();
    }
}
