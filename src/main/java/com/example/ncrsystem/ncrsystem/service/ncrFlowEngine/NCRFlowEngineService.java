package com.example.ncrsystem.ncrsystem.service.ncrFlowEngine;


import com.example.ncrsystem.ncrsystem.model.NCRRequest;

import java.math.BigInteger;

public interface NCRFlowEngineService {
    boolean checkMatrix(BigInteger departmentId);
    void startFlow(NCRRequest ncrRequest);
    void nextFlow(NCRRequest ncrRequest);
    void previousFlow(NCRRequest ncrRequest);
}
