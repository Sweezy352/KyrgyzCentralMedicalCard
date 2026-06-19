package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.response.AccessLogResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.AccessLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AccessLogMapper {

    @Mapping(target = "patientId", expression = "java(entity.getPatient() != null ? entity.getPatient().getId() : null)")
    @Mapping(target = "patientFio", expression = "java(entity.getPatient() != null ? entity.getPatient().getFio() : null)")
    @Mapping(target = "accessedById", expression = "java(entity.getAccessedByUser() != null ? entity.getAccessedByUser().getId() : null)")
    @Mapping(target = "accessedByFio", expression = "java(entity.getAccessedByUser() != null ? entity.getAccessedByUser().getFio() : null)")
    @Mapping(target = "organizationId", expression = "java(entity.getOrganization() != null ? entity.getOrganization().getId() : null)")
    @Mapping(target = "organizationName", expression = "java(entity.getOrganization() != null ? entity.getOrganization().getName() : null)")
    public abstract AccessLogResponse toDto(AccessLog entity);
}
