package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.NewsRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class NewsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "newsPictureFiles", ignore = true)
    public abstract News toEntity(NewsRequest request);

    @Mapping(target = "userId", expression = "java(entity.getUser().getId())")
    public abstract NewsResponse toDto(News entity);
}
