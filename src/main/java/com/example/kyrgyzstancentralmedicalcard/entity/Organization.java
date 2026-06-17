package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Organization extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrganizationType type;

    @Column(unique = true, nullable = false, length = 14)
    private String bin;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "contract_start")
    private LocalDate contractStart;

    @Column(name = "contract_end")
    private LocalDate contractEnd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<OrganizationUser> staff;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
