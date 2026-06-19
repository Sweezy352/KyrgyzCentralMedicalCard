package com.example.kyrgyzstancentralmedicalcard.dto.response;

import com.example.kyrgyzstancentralmedicalcard.dto.view.UserView;
import com.example.kyrgyzstancentralmedicalcard.entity.VisitType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MedicalVisitResponse(
        Long id,
        Long patientId,
        Long clinicId,
        String clinicName,
        UserView doctor,
        LocalDateTime visitDate,
        VisitType visitType,
        String chiefComplaint,
        String diagnosisPrimary,
        String diagnosisSecondary,
        String treatmentPlan,
        String notes,
        Integer durationMinutes,
        BigDecimal cost,
        LocalDateTime createdAt
) {}
