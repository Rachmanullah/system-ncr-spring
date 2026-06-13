package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "NCR_MATRIX_APPROVAL")
public class NCRMatrixApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_matrix_approval_seq")
    @SequenceGenerator(
            name = "ncr_matrix_approval_seq",
            sequenceName = "ncr_matrix_approval_seq",
            allocationSize = 1
    )
    @Column(name = "NCR_MATRIX_ID")
    private BigInteger ncrMatrixId;
    @Column(name = "NCR_MATRIX_CODE", nullable = false, length = 20)
    private String ncrMatrixCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Department department;
    @OneToMany(
            mappedBy = "ncrMatrixApproval",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<NCRMatrixDetail> details = new ArrayList<>();
    @Column(name = "STATUS")
    private BigInteger status;
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
        if (status == null) {
            status = BigInteger.ZERO;
        }
    }
}
