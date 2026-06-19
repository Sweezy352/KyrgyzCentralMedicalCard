package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.response.MedicalVisitResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.MedicalVisit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MedicalVisitMapper {

    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "patientId", expression = "java(entity.getPatient().getId())")
    @Mapping(target = "clinicId", expression = "java(entity.getClinic().getId())")
    @Mapping(target = "clinicName", expression = "java(entity.getClinic().getName())")
    @Mapping(target = "doctor", expression = "java(userMapper.toView(entity.getDoctor().getUser()))")
    public abstract MedicalVisitResponse toDto(MedicalVisit entity);
}
