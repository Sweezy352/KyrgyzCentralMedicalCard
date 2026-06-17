package com.example.kyrgyzstancentralmedicalcard.dto.request;

import com.example.kyrgyzstancentralmedicalcard.entity.VisitType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MedicalVisitRequest(
        @NotNull(message = "ID пациента не может быть пустым")
        Long patientId,
        @NotNull(message = "ID клиники не может быть пустым")
        Long clinicId,
        @NotNull(message = "ID врача не может быть пустым")
        Long doctorId,
        @NotNull(message = "Дата визита не может быть пустой")
        LocalDateTime visitDate,
        VisitType visitType,
        String chiefComplaint,
        String diagnosisPrimary,
        String diagnosisSecondary,
        String treatmentPlan,
        String notes,
        Integer durationMinutes,
        BigDecimal cost
) {}
