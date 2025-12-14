package com.example.kyrgyzstancentralmedicalcard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull(message = "ИНН не должен быть пустым")
    @NotBlank(message = "ИНН не должен быть пустым")
    @Size(max = 14, message = "ИНН должен быть 14 строк")
    private String inn;
    @NotNull(message = "ФИО не должно быть пустым")
    @NotBlank(message = "ФИО не должно быть пустым")
    private String fio;
    @NotNull(message = "Пароль не должен быть постым")
    private String password;
    private String gender;
    private LocalDate birthday;
    private String en;
    private String enName;
    private String blood;
    private String rh;
    private Long id;
}
