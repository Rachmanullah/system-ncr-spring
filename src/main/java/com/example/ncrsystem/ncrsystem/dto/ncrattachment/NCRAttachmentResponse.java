package com.example.ncrsystem.ncrsystem.dto.ncrattachment;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
@Builder
public class NCRAttachmentResponse {
    private BigInteger ncrAttachmentId;
    private BigInteger ncrId;
    private String fileName;
    private String originalFileName;
    private Long fileSize;
    private Date created;
}
