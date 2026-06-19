package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String inn;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false)
    private String password;

    @Column
    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "emergency_phone")
    private String emergencyPhone;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "rh_factor")
    private String rhFactor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "m2m_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<History> histories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userDoc")
    private List<History> historiesDoc;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Receipt> receipts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    private List<Receipt> receiptsDoctor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<AllergieEntity> allergieEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    private List<AllergieEntity> allergieEntitiesDoctors;
}
