package com.example.ncrsystem.ncrsystem.model;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @CreatedDate
    @Column(name = "NCR_DATE",nullable = false)
    private Date ncrDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTOR_ID")
    private User requestor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
    @NotBlank
    @Column(name = "TITLE", nullable = false, length = 150)
    private String ncrTitle;
    @Column(name = "PROJECT_NAME", length = 100)
    private String ncrProject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMPLEMENTATION_ID")
    private User implementationBy;
    @Column(name = "NCR_IMPLEMENTATION_DATE")
    private Date ncrImplementationDate;
    @Column(name="CATEGORY",length = 50)
    private String ncrCategory;
    @Column(name = "STATUS_CODE", length = 10)
    private String statusCode;
    @Column(name = "STATUS_NAME", length = 150)
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
    @OneToMany(mappedBy = "ncrRequest")
    private List<NCRLogs> ncrLogs;
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
