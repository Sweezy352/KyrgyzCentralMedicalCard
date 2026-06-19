package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.response.OrganizationUserResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.OrganizationUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrganizationUserMapper {

    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "user", expression = "java(userMapper.toView(entity.getUser()))")
    public abstract OrganizationUserResponse toDto(OrganizationUser entity);
}
