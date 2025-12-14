package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.HistoryRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.HistoryResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.History;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import lombok.RequiredArgsConstructor; // Добавляем RequiredArgsConstructor
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor // Используем для автоматической инъекции
public class HistoryMapper {
    private final UserRepository userRepository;
    private final UserMapper userMapper; // Внедряем UserMapper

    public History toEntity(HistoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }
        return History.builder()
                .name(request.getName())
                .description(request.getDescription())
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
                .userDoc(userMapper.toView(entity.getUserDoc())) // Используем userMapper.toView
                .dateCreated(entity.getDateCreated())
                .dateUpdated(entity.getDateUpdated())
                .build();
    }
}
