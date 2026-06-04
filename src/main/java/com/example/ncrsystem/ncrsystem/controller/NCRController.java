package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.NCRApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.service.ncrrequest.NCRService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@AllArgsConstructor
public class NCRController implements NCRApi {
    private final NCRService ncrService;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(ncrService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody NCRRequestDto request) {
        return ResponseHandler.success(ncrService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id,@Valid @RequestBody NCRRequestDto request) {
        return ResponseHandler.success(ncrService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(ncrService.delete(BigInteger.valueOf(id)));
    }
}
