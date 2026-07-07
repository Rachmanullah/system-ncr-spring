package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "NCR_PERMISSION",uniqueConstraints = @UniqueConstraint(columnNames = {"PERMISSION_CODE"}))
public class PermissionAction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_permission_seq")
    @SequenceGenerator(
            name = "ncr_permission_seq",
            sequenceName = "ncr_permission_seq",
            allocationSize = 1
    )
    @Column(name = "PERMISSION_ID")
    private BigInteger permissionId;
    @Column(name = "PERMISSION_CODE", nullable = false)
    private String permissionCode;
    @Column(name = "PERMISSION_DESCRIPTION")
    private String permissionDescription;
    @Column(name = "IS_ACTIVE")
    private BigInteger isActive;
    @Column(name="DELETED", nullable = true)
    private BigInteger deleted;
    @OneToMany(
            mappedBy = "permissionAction",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RolePermission> rolePermissions;
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
        if (isActive == null) {
            isActive = BigInteger.ZERO;
        }
    }
}
