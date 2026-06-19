package com.example.kyrgyzstancentralmedicalcard.dto.request;

import com.example.kyrgyzstancentralmedicalcard.entity.VisitType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MedicalVisitRequest(
        @NotNull(message = "ID пациента не может быть пустым")
        Long patientId,
        Long clinicId,
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
