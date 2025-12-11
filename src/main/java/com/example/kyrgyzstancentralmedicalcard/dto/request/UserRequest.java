package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull(message = "ИНН не должен быть пустым")
    @NotBlank(message = "ИНН не должен быть пустым")
    private String inn;
    @NotNull(message = "ФИО не должно быть пустым")
    @NotBlank(message = "ФИО не должно быть пустым")
    private String fio;
    @NotNull(message = "Пароль не должен быть постым")
    private String password;
}
