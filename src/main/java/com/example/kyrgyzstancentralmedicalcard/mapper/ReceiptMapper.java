package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.ReceiptDtoRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.ReceiptDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Receipt;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReceiptMapper {

    @Autowired
    protected UserMapper userMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "number", ignore = true)
    public abstract Receipt toEntity(ReceiptDtoRequest request);

    @Mapping(target = "userViewDoc", expression = "java(userMapper.toView(entity.getDoctor()))")
    public abstract ReceiptDtoResponse toDto(Receipt entity);
}
