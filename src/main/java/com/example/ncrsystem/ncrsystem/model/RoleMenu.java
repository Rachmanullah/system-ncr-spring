package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "NCR_ROLE_MENU")
public class RoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_menu_seq")
    @SequenceGenerator(
            name = "role_menu_seq",
            sequenceName = "role_menu_seq",
            allocationSize = 1
    )
    @Column(name = "ROLE_MENU_ID")
    private BigInteger roleMenuId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ROLE_ID",
            nullable = false
    )
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "MENU_ID",
            nullable = false
    )
    private Menu menu;
    @Column(name="STATUS")
    private BigInteger status;
    @Column(name="DELETED")
    private BigInteger deleted;
    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (status == null) {
            status = BigInteger.ZERO;
        }
        if (deleted == null) {
            deleted = BigInteger.ZERO;
        }
    }
}
