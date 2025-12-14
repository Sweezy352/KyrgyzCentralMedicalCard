package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "user_insurances")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserInsurances extends  BaseEntity {

    @ManyToOne
    @JoinColumn(name = "medicine_insurance")
    private MedicineInsurance medicineInsurance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_active", nullable = false)
    @CreationTimestamp
    private LocalDate dateActive;

    @Column(name = "date_expire", nullable = false)
    private LocalDate dateExpire;

    @Column(name = "active", nullable = false)
    private boolean active;

    @PrePersist
    private  void prePersist() {
        this.active = true;
    }
}
