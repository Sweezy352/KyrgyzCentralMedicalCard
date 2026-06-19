package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "histories", ignore = true)
    @Mapping(target = "historiesDoc", ignore = true)
    @Mapping(target = "receipts", ignore = true)
    @Mapping(target = "receiptsDoctor", ignore = true)
    @Mapping(target = "allergieEntities", ignore = true)
    @Mapping(target = "allergieEntitiesDoctors", ignore = true)
    public abstract User toEntity(UserRequest request);

    public abstract UserResponse toResponse(User entity);

    @Mapping(target = "roleName", expression = "java(entity.getRoles().get(0).getRoleName())")
    public abstract UserView toView(User entity);
}
