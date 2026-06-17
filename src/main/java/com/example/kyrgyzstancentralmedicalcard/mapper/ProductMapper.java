package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.ProductRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ProductResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Product;
import com.example.kyrgyzstancentralmedicalcard.repository.CompanyRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected CompanyRepository companyRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    public abstract Product toEntity(ProductRequest request);

    @AfterMapping
    protected void setCompany(ProductRequest request, @MappingTarget Product product) {
        product.setCompany(companyRepository.findById(request.companyId()).orElseThrow());
    }

    @Mapping(target = "companyId", expression = "java(entity.getCompany().getId())")
    public abstract ProductResponse toDto(Product entity);
}
