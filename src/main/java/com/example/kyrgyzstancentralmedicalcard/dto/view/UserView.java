package com.example.kyrgyzstancentralmedicalcard.dto.view;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    private Long id;
    private String inn;
    private String fio;
    private String roleName;
}
