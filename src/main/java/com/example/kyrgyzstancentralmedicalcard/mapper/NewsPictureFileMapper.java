package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.NewsRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsPictureFileDtoResponse;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.entity.NewsPictureFiles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class NewsPictureFileMapper {

    public NewsPictureFiles toEntity(MultipartFile request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return NewsPictureFiles.builder()
                .originalFileName(request.getOriginalFilename())
                //В сервисе NewsServiceImpl сделать поиск по айди и засетить пользователя
                .mimeType(request.getContentType())
                .build();
    }

    public NewsPictureFileDtoResponse toDto(NewsPictureFiles entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return NewsPictureFileDtoResponse.builder()
                .id(entity.getId())
                .originalFileName(entity.getOriginalFileName())
                .mimeType(entity.getMimeType())
                .build();
    }
}
