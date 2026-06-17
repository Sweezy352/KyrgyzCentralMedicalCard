package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.OrganizationRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.OrganizationResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    public abstract Organization toEntity(OrganizationRequest request);

    public abstract OrganizationResponse toDto(Organization entity);
}
