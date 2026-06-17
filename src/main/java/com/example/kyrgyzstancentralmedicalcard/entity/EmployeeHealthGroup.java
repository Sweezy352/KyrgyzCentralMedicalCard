package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "employee_health_groups")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHealthGroup extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private User patient;

    @Column(name = "employee_id")
    private String employeeId;

    @Column
    private String department;

    @Column
    private String position;

    @Column(name = "health_group", length = 10)
    private String healthGroup;

    @Column(name = "last_checkup_date")
    private LocalDate lastCheckupDate;

    @Column(name = "next_checkup_date")
    private LocalDate nextCheckupDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "joined_at")
    private LocalDate joinedAt;
}
