package com.example.ncrsystem.ncrsystem.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name="NCR_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
    )
    @Column(name="USER_ID")
    private BigInteger userId;
    @NotBlank
    @Column(name="USERNAME", unique = true, length = 50, nullable = false)
    private String username;
    @NotBlank
    @Column(name="PASSWORD", length = 150, nullable = false)
    private String password;
    @Column(name="FULLNAME", length = 150, nullable = true)
    private String fullname;
    @Email
    @Column(name="EMAIL", length = 100, nullable = true)
    private String email;
    @Column(name="STATUS", nullable = true)
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
    @Column(name="LAST_MODIFED_BY_LOGIN_HISTORY", nullable = true)
    private BigInteger lastModifiedByLoginHistory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;
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
    public User(){};
    public User(String username, String password, String fullname, String email, Role role, Department department, BigInteger status){
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
        this.department = department;
        this.status = status;
    };

    public BigInteger getUserId(){return this.userId;}
    public void setUserId(BigInteger userId){this.userId = userId;}
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username = username;}
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password = password;}
    public String getFullname(){return this.fullname;}
    public void setFullname(String fullname){this.fullname = fullname;}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}
    public Role getRole(){return this.role;}
    public void setRole(Role role){this.role = role;}
    public Department getDepartment(){return this.department;}
    public void setDepartmentId(Department department){this.department = department;}
    public BigInteger getStatus(){return this.status;}
    public void setStatus(BigInteger status){this.status = status;}
}
