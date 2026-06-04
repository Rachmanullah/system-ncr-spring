package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigInteger;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "NCR_REQUEST_MAIN")
public class NCRRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_request_main_seq")
    @SequenceGenerator(
            name = "ncr_request_main_seq",
            sequenceName = "ncr_request_main_seq",
            allocationSize = 1
    )
    @Column(name = "NCR_ID")
    private BigInteger ncrId;
    @NotBlank
    @Column(name = "NCR_NUMBER", nullable = false, length = 20)
    private String ncrNumber;
    @NotBlank
    @Column(name = "TITLE", nullable = false, length = 150)
    private String ncrTitle;
    @Column(name = "PROJECT_NAME", length = 100)
    private String ncrProject;
    @CreatedDate
    @Column(name = "NCR_DATE",nullable = false)
    private Date ncrDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTOR_ID")
    private User requestor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMPLEMENTATION_ID",nullable = true)
    private User implementationBy;
    @Column(name = "STATUS_CODE", length = 10)
    private String statusCode;
    @Column(name = "STATUS_NAME", length = 100)
    private String statusName;
    @Column(name = "RUNNING_NUMBER")
    private Integer runningNumber;
    @OneToOne(
            mappedBy = "ncrRequest",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private NCRRequestDetail detail;
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
        if(runningNumber == null){
            runningNumber = 0;
        }
    }
}
