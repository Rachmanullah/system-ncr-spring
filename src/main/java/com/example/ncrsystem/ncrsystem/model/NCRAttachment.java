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
@Table(name = "NCR_REQUEST_MAIN")
public class NCRAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncr_attachment_seq")
    @SequenceGenerator(
            name = "ncr_attachment_seq",
            sequenceName = "ncr_attachment_seq",
            allocationSize = 1
    )
    @Column(name = "NCR_ATTACHMENT_ID")
    private BigInteger ncrAttachmentId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "NCR_ID",
            nullable = false,
            unique = true
    )
    private NCRRequest ncrRequest;
    @Column(name = "FILE_NAME", length = 150)
    private String fileName;
    @Column(name = "FILE_PATH", length = 255)
    private String filePath;
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
