package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "m2m_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<History> histories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userDoc")
    private List<History> historiesDoc;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserInsurances> userInsurances;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Receipt> receipts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    private List<Receipt> receiptsDoctor;
}
