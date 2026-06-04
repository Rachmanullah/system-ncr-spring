package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/ncr")
public interface NCRApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@RequestBody NCRRequestDto request);
    @PutMapping("/{ncrId}")
    ResponseEntity<?> update(@PathVariable("ncrId") Long id, @RequestBody NCRRequestDto request);
    @DeleteMapping("/{ncrId}")
    ResponseEntity<?> delete(@PathVariable("ncrId") Long id);
}
