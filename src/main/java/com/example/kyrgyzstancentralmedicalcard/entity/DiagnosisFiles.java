package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pdf_file_diagnoses")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisFiles extends BaseEntity{

    @Column(name = "original_file_name", nullable = false, unique = true)
    private String originalFileName;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;
}
