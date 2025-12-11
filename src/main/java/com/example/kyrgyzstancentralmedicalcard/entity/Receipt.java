package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "receipts")
public class Receipt extends BaseEntity{
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor;
    @Column(name = "date_created")
    private LocalDate dateCreated;
}
