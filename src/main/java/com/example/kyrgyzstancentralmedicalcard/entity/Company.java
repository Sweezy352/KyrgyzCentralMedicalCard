package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_created")
    private LocalDate dateCreated;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<User> employees;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<History> histories;

    @PrePersist
    public void prePersist() {
        this.dateCreated = LocalDate.now();
    }


}
