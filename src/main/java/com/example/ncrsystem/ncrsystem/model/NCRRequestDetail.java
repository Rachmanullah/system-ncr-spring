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
@Table(name = "NCR_REQUEST_DETAIL")
public class NCRRequestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_request_detail_seq")
    @SequenceGenerator(
            name = "ncr_request_detail_seq",
            sequenceName = "ncr_request_detail_seq",
            allocationSize = 1
    )
    @Column(name = "NCR_DETAIL_ID")
    private BigInteger ncrDetailId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "NCR_ID",
            nullable = false,
            unique = true
    )
    private NCRRequest ncrRequest;
    @NotBlank
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIORITY_ID")
    private Priority priority;
    @Column(name = "AS_IS", columnDefinition = "TEXT")
    private String asIs;
    @Column(name = "TO_BE", columnDefinition = "TEXT")
    private String toBe;
    @Column(name = "BENEFIT", columnDefinition = "TEXT")
    private String benefit;
    @Column(name = "IMPACT", columnDefinition = "TEXT")
    private String impact;
    @Column(name = "FINANCIAL_IMPACT", columnDefinition = "TEXT")
    private String financialImpact;
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
