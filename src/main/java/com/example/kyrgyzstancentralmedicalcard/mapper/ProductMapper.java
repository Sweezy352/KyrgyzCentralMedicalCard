package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.ProductRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ProductResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.entity.Product;
import com.example.kyrgyzstancentralmedicalcard.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<Product, ProductRequest, ProductResponse> {

    private final CompanyRepository companyRepository;

    @Autowired
    public ProductMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Product toEntity(ProductRequest request) {
        Product entity = mapFields(request, new Product());
        if (request.getCompanyId() != null) {
            Company company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found with ID: " + request.getCompanyId()));
            entity.setCompany(company);
        }

        return entity;
    }

    @Override
    public ProductResponse toResponse(Product entity) {
        ProductResponse response = mapFields(entity, new ProductResponse());
        if (entity.getCompany() != null) {
            response.setCompanyId(entity.getCompany().getId());
        }
        return response;
    }
}
