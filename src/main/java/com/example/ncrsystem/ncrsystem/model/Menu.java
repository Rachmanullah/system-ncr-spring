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
@Table(name = "NCR_MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_menu_seq")
    @SequenceGenerator(
            name = "ncr_menu_seq",
            sequenceName = "ncr_menu_seq",
            allocationSize = 1
    )
    @Column(name = "MENU_ID")
    private BigInteger menuId;
    @Column(name = "MENU_TITLE", length = 30, nullable = false)
    private String menuTitle;
    @Column(name = "MENU_ICON", length = 30)
    private String menuIcon;
    @Column(name = "MENU_ROUTE", length = 50)
    private String menuRoute;
    @Column(name = "MENU_PARENT_ID")
    private Integer menuParentId;
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
