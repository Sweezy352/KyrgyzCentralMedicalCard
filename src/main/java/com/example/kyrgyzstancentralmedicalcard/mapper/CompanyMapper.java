package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.CompanyRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.CompanyResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Company;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "histories", ignore = true)
    @Mapping(target = "user", ignore = true)
    public abstract Company toEntity(CompanyRequest request);

    @AfterMapping
    protected void setUser(CompanyRequest request, @MappingTarget Company company) {
        company.setUser(userRepository.findById(request.userId()).orElseThrow());
    }

    @Mapping(target = "userId", expression = "java(entity.getUser().getId())")
    public abstract CompanyResponse toDto(Company entity);
}
