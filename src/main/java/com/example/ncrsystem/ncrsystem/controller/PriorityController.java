package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.PriorityApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.priority.PriorityRequest;
import com.example.ncrsystem.ncrsystem.service.priority.PriorityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class PriorityController implements PriorityApi {
    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(priorityService.findAll());
    }

    @Override
    public ResponseEntity<?> create( @Valid @RequestBody PriorityRequest request) {
        return ResponseHandler.success(priorityService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id, @Valid @RequestBody PriorityRequest request) {
        return ResponseHandler.success(priorityService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(priorityService.delete(BigInteger.valueOf(id)));
    }
}
