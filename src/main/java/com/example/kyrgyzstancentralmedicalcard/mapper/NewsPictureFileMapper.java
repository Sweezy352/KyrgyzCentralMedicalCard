package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsPictureFileDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.NewsPictureFiles;
import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public abstract class NewsPictureFileMapper {

    public NewsPictureFiles toEntity(MultipartFile file) {
        if (file == null) return null;
        return NewsPictureFiles.builder()
                .originalFileName(file.getOriginalFilename())
                .mimeType(file.getContentType())
                .build();
    }

    @Mapping(target = "id", source = "id")
    public abstract NewsPictureFileDtoResponse toDto(NewsPictureFiles entity);
}
