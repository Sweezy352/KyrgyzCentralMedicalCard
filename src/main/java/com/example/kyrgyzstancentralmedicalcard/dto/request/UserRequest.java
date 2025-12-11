package com.example.kyrgyzstancentralmedicalcard.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String inn;
    private String fio;
    private String password;
    private String roleName;
}
