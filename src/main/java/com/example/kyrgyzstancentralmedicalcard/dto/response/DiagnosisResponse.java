package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView; // Импортируем UserView
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisResponse {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private UserView userDoc; // Изменено с Long userDocId на UserView userDoc
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private String status;
}
