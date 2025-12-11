package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.ProductRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ProductResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Product;
import com.example.kyrgyzstancentralmedicalcard.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CompanyRepository companyRepository;

    @Autowired
    public ProductMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Product toEntity(ProductRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return Product.builder()
                .productName(request.getProductName())
                .amount(request.getAmount())
                .company(companyRepository.findById(request.getCompanyId()).get())
                .build();
    }

    public ProductResponse toDto(Product entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return ProductResponse.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .amount(entity.getAmount())
                .dateCreated(entity.getDateCreated())
                .dateUpdated(entity.getDateUpdated())
                .companyId(entity.getCompany().getId())
                .build();
    }
}
