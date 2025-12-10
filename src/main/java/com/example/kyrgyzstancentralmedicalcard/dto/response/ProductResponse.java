package com.example.kyrgyzstancentralmedicalcard.dto.response;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String productName;
    private Long amount;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private Long companyId;
}
