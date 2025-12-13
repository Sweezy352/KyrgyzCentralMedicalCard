package com.example.kyrgyzstancentralmedicalcard.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsPictureFileDtoResponse {
    private Long id;
    private String originalFileName;
    private String mimeType;
}
