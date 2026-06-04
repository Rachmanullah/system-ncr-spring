package com.example.ncrsystem.ncrsystem.service.ncrrequest;

import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestResponse;

import java.math.BigInteger;
import java.util.List;

public interface NCRService {
    List<NCRRequestResponse> findAll();
    NCRRequestResponse create(NCRRequestDto request);
    NCRRequestResponse update(BigInteger ncrId, NCRRequestDto request);
    NCRRequestResponse delete(BigInteger ncrId);
}
