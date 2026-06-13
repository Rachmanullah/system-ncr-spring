package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.NCRMatrixApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixRequest;
import com.example.ncrsystem.ncrsystem.service.ncrmatrix.NCRMatrixService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class NCRMatrixController implements NCRMatrixApi {
    private final NCRMatrixService ncrMatrixService;

    public NCRMatrixController(NCRMatrixService ncrMatrixService) {
        this.ncrMatrixService = ncrMatrixService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(ncrMatrixService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody NCRMatrixRequest request) {
        return ResponseHandler.success(ncrMatrixService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long matrixId,@Valid @RequestBody NCRMatrixRequest request) {
        return ResponseHandler.success(ncrMatrixService.update(BigInteger.valueOf(matrixId), request));
    }

    @Override
    public ResponseEntity<?> delete(Long matrixId) {
        return ResponseHandler.success(ncrMatrixService.delete(BigInteger.valueOf(matrixId)));
    }
}
