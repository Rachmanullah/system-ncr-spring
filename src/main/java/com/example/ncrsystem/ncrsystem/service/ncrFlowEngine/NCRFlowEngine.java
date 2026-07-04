package com.example.ncrsystem.ncrsystem.service.ncrFlowEngine;


import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.model.NCRRequest;

import java.math.BigInteger;

public interface NCRFlowEngine {
    boolean checkMatrix(BigInteger departmentId);
    void startFlow(NCRRequest ncrRequest);
}
