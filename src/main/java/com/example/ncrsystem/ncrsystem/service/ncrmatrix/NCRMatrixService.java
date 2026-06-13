package com.example.ncrsystem.ncrsystem.service.ncrmatrix;

import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixRequest;
import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixResponse;

import java.math.BigInteger;
import java.util.List;

public interface NCRMatrixService {
    List<NCRMatrixResponse> findAll();
    NCRMatrixResponse create(NCRMatrixRequest ncrMatrixRequest);
    NCRMatrixResponse update(BigInteger ncrMatrixId, NCRMatrixRequest ncrMatrixRequest);
    NCRMatrixResponse delete(BigInteger ncrMatrixId);
}
