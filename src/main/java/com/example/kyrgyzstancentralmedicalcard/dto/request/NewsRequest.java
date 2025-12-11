package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {
    @NotNull(message = "Название не должно быть пустым")
    @NotBlank(message = "Название не должно быть пустым")
    private String name;
    @NotNull(message = "Описание не должно быть пустым")
    @NotBlank(message = "Описание не должно быть пустым")
    private String description;
}
