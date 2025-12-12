package com.example.kyrgyzstancentralmedicalcard.mapper;

import com.example.kyrgyzstancentralmedicalcard.dto.request.DiagnosisRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.DiagnosisResponse;
import com.example.kyrgyzstancentralmedicalcard.entity.Diagnosis;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DiagnosisMapper {

    private final UserRepository userRepository;

    public DiagnosisMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Diagnosis toEntity(DiagnosisRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Проблема");
        }

        return Diagnosis.builder()
                .name(request.getName())
                .description(request.getDescription())
                //Сам доктор будет проверять, поэтому будем брать состояние пользователя из SecurityContext
                //Status будет выставляться автоматически ACTUAL
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
                .userDocId(entity.getUserDoc().getId())
                .dateCreated(entity.getDateCreated())
                .dateUpdated(entity.getDateUpdated())
                .status(entity.getStatus())
                .build();
    }
}
