package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "NCR_PRIORITY")
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priority_seq")
    @SequenceGenerator(
            name = "priority_seq",
            sequenceName = "priority_seq",
            allocationSize = 1
    )
    @Column(name = "PRIORITY_ID")
    private BigInteger priorityId;
    @NotBlank
    @Column(name = "PRIORITY_CODE", length = 20, nullable = false)
    private String priorityCode;
    @Column(name = "SLA")
    private Integer sla;
    @Column(name="DELETED", nullable = true)
    private BigInteger deleted;
    @CreationTimestamp
    @Column(name="CREATED", nullable = true)
    private Date created;
    @Column(name="CREATED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger createdByLoginHistory;
    @UpdateTimestamp
    @Column(name="LAST_MODIFIED", nullable = true)
    private Date latModified;
    @Column(name="LAST_MODIFIED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger lastModifiedByLoginHistory;
    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (deleted == null) {
            deleted = BigInteger.ZERO;
        }
    }
}
