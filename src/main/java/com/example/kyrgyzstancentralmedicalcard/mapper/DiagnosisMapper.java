package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.DiagnosisRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.DiagnosisResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import lombok.RequiredArgsConstructor; // Добавляем RequiredArgsConstructor
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor // Используем для автоматической инъекции
public class DiagnosisMapper {

    private final UserRepository userRepository;
    private final UserMapper userMapper; // Внедряем UserMapper

    public Diagnosis toEntity(DiagnosisRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return Diagnosis.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public DiagnosisResponse toDto(Diagnosis entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return DiagnosisResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .userId(entity.getUser().getId())
                .userDoc(userMapper.toView(entity.getUserDoc())) // Используем userMapper.toView
                .dateCreated(entity.getDateCreated())
                .dateUpdated(entity.getDateUpdated())
                .status(entity.getStatus())
                .build();
    }
}
