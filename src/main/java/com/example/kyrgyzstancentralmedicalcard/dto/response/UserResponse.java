package com.example.kyrgyzstancentralmedicalcard.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String fio;
    private String inn;
}
