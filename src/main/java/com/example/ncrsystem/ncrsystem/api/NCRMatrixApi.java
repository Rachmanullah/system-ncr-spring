package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval.NCRMatrixRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/ncr/matrix")
public interface NCRMatrixApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@RequestBody NCRMatrixRequest request);
    @PutMapping("/{matrixId}")
    ResponseEntity<?> update(@PathVariable("matrixId") Long matrixId,@RequestBody NCRMatrixRequest request);
    @DeleteMapping("/{matrixId}")
    ResponseEntity<?> delete(@PathVariable("matrixId") Long matrixId);
}
