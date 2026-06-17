package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.DiagnosisRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.DiagnosisResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class DiagnosisMapper {

    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "userDoc", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract Diagnosis toEntity(DiagnosisRequest request);

    @Mapping(target = "userId", expression = "java(entity.getUser().getId())")
    @Mapping(target = "userDoc", expression = "java(userMapper.toView(entity.getUserDoc()))")
    public abstract DiagnosisResponse toDto(Diagnosis entity);
}
