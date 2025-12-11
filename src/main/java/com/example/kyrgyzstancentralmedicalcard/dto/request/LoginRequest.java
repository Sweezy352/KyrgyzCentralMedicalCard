package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull(message = "ИНН не должен быть пустым")
    @NotBlank(message = "ИНН не должен быть пустым")
    String inn;
    @NotNull(message = "Пароль не должен быть пустым")
    @NotBlank(message = "Пароль не должен быть пустым")
    String password;
}
