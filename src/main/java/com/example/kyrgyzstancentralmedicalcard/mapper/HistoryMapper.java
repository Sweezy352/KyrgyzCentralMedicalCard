package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.HistoryRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.HistoryResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.History;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class HistoryMapper {

    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "userDoc", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    public abstract History toEntity(HistoryRequest request);

    @Mapping(target = "userId", expression = "java(entity.getUser().getId())")
    @Mapping(target = "userDoc", expression = "java(userMapper.toView(entity.getUserDoc()))")
    public abstract HistoryResponse toDto(History entity);
}
