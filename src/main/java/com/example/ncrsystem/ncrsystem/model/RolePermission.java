package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "NCR_ROLE_PERMISSION",uniqueConstraints = @UniqueConstraint(columnNames = {"ROLE_ID", "PERMISSION_ID"}))
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_role_permission_seq")
    @SequenceGenerator(
            name = "ncr_role_permission_seq",
            sequenceName = "ncr_role_permission_seq",
            allocationSize = 1
    )
    @Column(name = "ROLE_PERMISSION_ID")
    private BigInteger rolePermissionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ROLE_ID",
            nullable = false
    )
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PERMISSION_ID", nullable = false)
    private PermissionAction permissionAction;
    @Column(name="STATUS", nullable = true)
    private BigInteger status;
    @Column(name="DELETED", nullable = true)
    private BigInteger deleted;
    @CreationTimestamp
    @Column(name="CREATED", nullable = true)
    private Date created;
    @Column(name="CREATED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger createdByLoginHistory;
    @UpdateTimestamp
    @Column(name="LAST_MODIFIED", nullable = true)
    private Date lastModified;
    @Column(name="LAST_MODIFIED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger lastModifiedByLoginHistory;
    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (deleted == null) {
            deleted = BigInteger.ZERO;
        }
        if (status == null) {
            status = BigInteger.ZERO;
        }
    }
}
