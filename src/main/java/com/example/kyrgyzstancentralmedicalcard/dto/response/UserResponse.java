package com.example.kyrgyzstancentralmedicalcard.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String fio;
    private String inn;
    private String gender;
    private LocalDate birthday;
    private String en;
    private String enName;
    private String blood;
    private String rh;
}
