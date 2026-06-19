package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.response.EmployeeHealthGroupResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.EmployeeHealthGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class EmployeeHealthGroupMapper {

    @Mapping(target = "organizationId", expression = "java(entity.getOrganization().getId())")
    @Mapping(target = "organizationName", expression = "java(entity.getOrganization().getName())")
    @Mapping(target = "patientId", expression = "java(entity.getPatient().getId())")
    @Mapping(target = "patientFio", expression = "java(entity.getPatient().getFio())")
    public abstract EmployeeHealthGroupResponse toDto(EmployeeHealthGroup entity);
}
