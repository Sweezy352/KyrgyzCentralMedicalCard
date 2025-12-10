package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news_picture_files")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsPictureFiles extends BaseEntity{

    @Column(name = "original_file_name", nullable = false, unique = true)
    private String originalFileName;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
}
