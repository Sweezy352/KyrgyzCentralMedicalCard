package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "medicine_insurances")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInsurance extends BaseEntity{

    @Column(name = "insurance_name", nullable = false)
    private String insuranceName;

    @Column(nullable = false)
    private BigDecimal price;
}
