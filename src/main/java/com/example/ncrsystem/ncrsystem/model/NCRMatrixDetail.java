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
@Table(name = "NCR_MATRIX_APPROVAL_DETAIL")
public class NCRMatrixDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_matrix_detail_seq")
    @SequenceGenerator(
            name = "ncr_matrix_detail_seq",
            sequenceName = "ncr_matrix_detail_seq",
            allocationSize = 1
    )
    @Column(name = "NCR_MATRIX_DETAIL_ID")
    private BigInteger ncrMatrixDetailId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NCR_MATRIX_ID", nullable = false)
    private NCRMatrixApproval ncrMatrixApproval;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPROVER_ID", nullable = false)
    private User approver;
    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;
    @Column(name = "REJECT_TO_ORDER_NUMBER")
    private Integer rejectToOrderNumber;
    @Column(name="DELETED")
    private BigInteger deleted;
    @CreationTimestamp
    @Column(name="CREATED")
    private Date created;
    @Column(name="CREATED_BY_LOGIN_HISTORY")
    private BigInteger createdByLoginHistory;
    @UpdateTimestamp
    @Column(name="LAST_MODIFIED")
    private Date latModified;
    @Column(name="LAST_MODIFIED_BY_LOGIN_HISTORY")
    private BigInteger lastModifiedByLoginHistory;
    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (deleted == null) {
            deleted = BigInteger.ZERO;
        }
    }
}
