package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Entity
@Table(name = "medicine_insurances")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInsurance extends BaseEntity{

    @Column(name = "insurance_name", nullable = false)
    private String insuranceName;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "description", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String description;
}
