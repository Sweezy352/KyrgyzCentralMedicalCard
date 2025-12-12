package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "allergies")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllergieEntity extends BaseEntity{
    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @Column(name = "date_updated")
    private LocalDate dateUpdated;
    @Column
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor;

    @PrePersist
    public void prePersist(){
        this.dateCreated = LocalDate.now();
        this.status = true;
    }
}
