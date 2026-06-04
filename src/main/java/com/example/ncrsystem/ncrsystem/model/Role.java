package com.example.ncrsystem.ncrsystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NCR_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(
            name = "role_seq",
            sequenceName = "role_seq",
            allocationSize = 1
    )
    @Column(name = "ROLE_ID")
    private BigInteger roleId;
    @Column(name = "ROLE_NAME", length = 20, nullable = false)
    private String roleName;
    @Column(name="STATUS")
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
    private Date latModified;
    @Column(name="LAST_MODIFIED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger lastModifiedByLoginHistory;
    @OneToMany(mappedBy = "role")
    private List<User> users;
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

    public Role(){}
    public Role(String roleName, BigInteger status){
        this.roleName = roleName;
        this.status = status;
    }

    public BigInteger getRoleId(){return this.roleId;}
    public void setRoleId(BigInteger roleId){this.roleId = roleId;}
    public String getRoleName(){return this.roleName;}
    public void setRoleName(String roleName){this.roleName = roleName;}
    public BigInteger getStatus(){return this.status;}
    public void setStatus(BigInteger status){this.status = status;}
}
