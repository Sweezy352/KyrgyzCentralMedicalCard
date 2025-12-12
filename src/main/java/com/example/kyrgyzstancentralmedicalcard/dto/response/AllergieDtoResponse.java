package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllergieDtoResponse {
    private Long id;
    private String name;
    private String description;
    private UserView userViewDoc;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private Boolean status;
}
