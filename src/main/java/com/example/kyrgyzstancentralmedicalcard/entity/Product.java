package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{

    @Column(name = "product_name", nullable = false)
    private String productName;

    private Long amount;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Column(name = "date_updated")
    private ZonedDateTime dateUpdated;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
