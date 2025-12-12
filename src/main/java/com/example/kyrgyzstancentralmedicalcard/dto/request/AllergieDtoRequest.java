package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllergieDtoRequest {
    @NotNull(message = "Не может быть пустым")
    @NotBlank(message = "Не может быть пустым ")
    private String name;
    @NotNull(message = "Не может быть пустым")
    @NotBlank(message = "Не может быть пустым")
    private String description;
}
