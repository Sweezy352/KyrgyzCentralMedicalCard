package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.response.ConsentResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.PatientConsent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PatientConsentMapper {

    @Mapping(target = "organizationId", expression = "java(entity.getOrganization().getId())")
    @Mapping(target = "organizationName", expression = "java(entity.getOrganization().getName())")
    public abstract ConsentResponse toDto(PatientConsent entity);
}
