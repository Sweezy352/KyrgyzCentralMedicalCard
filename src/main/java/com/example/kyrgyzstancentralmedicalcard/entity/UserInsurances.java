package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_insurances")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInsurances extends  BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medicine_insurance")
    private MedicineInsurance medicineInsurance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_active")
    private LocalDate dateActive;

    @Column(name = "date_expire")
    private LocalDate dateExpire;
}
