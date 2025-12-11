package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.NewsRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.NewsResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.News;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    private final UserRepository userRepository;

    @Autowired
    public NewsMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public News toEntity(NewsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return News.builder()
                .name(request.getName())
                .description(request.getDescription())
                .user(userRepository.findById(request.getUserId()).get())
                .build();
    }

    public NewsResponse toDto(News entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return NewsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .dateCreated(entity.getDateCreated())
                .userId(entity.getUser().getId())
                .build();
    }
}
