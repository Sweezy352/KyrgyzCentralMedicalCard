package com.example.kyrgyzstancentralmedicalcard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "roles")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Override
    public @Nullable String getAuthority() {
        return roleName;
    }
}
