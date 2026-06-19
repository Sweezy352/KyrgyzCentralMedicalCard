package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.AllergieDtoRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.AllergieDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;
import com.example.kyrgyzstancentralmedicalcard.entity.AllergieEntity;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AllergieMapper {

    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract AllergieEntity toEntity(AllergieDtoRequest request);

    @Mapping(target = "userViewDoc", expression = "java(userMapper.toView(entity.getUser()))")
    public abstract AllergieDtoResponse toDtoResponse(AllergieEntity entity);
}
