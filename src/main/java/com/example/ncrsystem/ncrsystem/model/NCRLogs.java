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
@Table(name = "NCR_LOGS")
public class NCRLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_logs_seq")
    @SequenceGenerator(
            name = "ncr_logs_seq",
            sequenceName = "ncr_logs_seq",
            allocationSize = 1
    )
    @Column(name = "NCR_LOGS_ID")
    private BigInteger ncrLogId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NCR_ID", nullable = false)
    private NCRRequest ncrRequest;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @Column(name = "STATUS_LOGS", length = 150)
    private String statusName;
    @Column(name = "NOTES", length = 100)
    private String notes;
    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;
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
