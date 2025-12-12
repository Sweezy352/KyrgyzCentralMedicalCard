package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDtoResponse {
    private Long id;
    private String number;
    private String name;
    private String description;
    private UserView userViewDoc;
    private LocalDate dateCreated;
}
