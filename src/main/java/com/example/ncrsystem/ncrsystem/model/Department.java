package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name="NCR_DEPARTMENT")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(
            name = "department_seq",
            sequenceName = "department_seq",
            allocationSize = 1
    )
    @Column(name = "DEPARTMENT_ID")
    private BigInteger departmentId;
    @Column(name = "DEPARTMENT_CODE", length=10)
    private String departmentCode;
    @NotBlank
    @Column(name = "DEPARTMENT_NAME", length = 100, nullable = false)
    private String departmentName;
    @Column(name="STATUS", nullable = true)
    private BigInteger status;
    @Column(name="DELETED", nullable = true)
    private BigInteger deleted;
    @CreationTimestamp
    @Column(name="CREATED", nullable = true)
    private LocalDateTime created;
    @Column(name="CREATED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger createdByLoginHistory;
    @UpdateTimestamp
    @Column(name="LAST_MODIFIED", nullable = true)
    private LocalDateTime latModified;
    @Column(name="LAST_MODIFIED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger lastModifiedByLoginHistory;
    @OneToMany(mappedBy = "department")
    private List<User> users;
    @OneToMany(mappedBy = "department")
    private List<NCRRequest> ncrRequests;
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
    public Department(){};
    public Department(String departmentName, BigInteger status){
        this.departmentName = departmentName;
        this.status = status;
    }

    public BigInteger getDepartmentId(){return this.departmentId;}
    public void setDepartmentId(BigInteger departmentId){this.departmentId = departmentId;}
    public String getDepartmentName(){return this.departmentName;}
    public void setDepartmentName(String departmentName){this.departmentName = departmentName;}
    public BigInteger getStatus(){return this.status;}
    public void setStatus(BigInteger status){this.status = status;}
}
