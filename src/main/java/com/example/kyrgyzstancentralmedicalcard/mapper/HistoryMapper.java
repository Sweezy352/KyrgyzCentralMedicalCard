package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.HistoryRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.HistoryResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.History;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class HistoryMapper {
    private final UserRepository userRepository;

    public HistoryMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public History toEntity(HistoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return History.builder()
                .name(request.getName())
                .description(request.getDescription())
                .user(userRepository.findById(request.getUserId()).get())
                //Сам доктор будет проверять, поэтому будем брать состояние пользователя из SecurityContext
                .build();
    }

    public HistoryResponse toDto(History entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return HistoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .userId(entity.getUser().getId())
                .userDocId(entity.getUserDoc().getId())
                .dateCreated(entity.getDateCreated())
                .dateUpdated(entity.getDateUpdated())
                .build();
    }
}
