package com.example.ncrsystem.ncrsystem.dto.ncrattachment;

import lombok.Data;

import java.math.BigInteger;

@Data
public class NCRAttachmentRequest {
    private final BigInteger ncrAttachmentId;
    private final BigInteger ncrId;
    private final String filename;
    private final String filepath;
}
