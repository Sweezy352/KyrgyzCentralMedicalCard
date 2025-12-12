package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisRequest {
    @NotNull(message = "Название диагноза не может быть пустым")
    @NotBlank(message = "Название диагноза не может быть пустым")
    private String name;
    @NotNull(message = "Описание диагноза не может быть пустым")
    @NotBlank(message = "Описание диагноза не может быть пустым")
    private String description;
    //private Long userId; -- Через PathVariable
}
