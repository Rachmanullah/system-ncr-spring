package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.priority.PriorityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/priority")
public interface PriorityApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@RequestBody PriorityRequest request);
    @PutMapping("/{priorityId}")
    ResponseEntity<?> update(@PathVariable("priorityId") Long id, @RequestBody PriorityRequest request);
    @DeleteMapping("/{priorityId}")
    ResponseEntity<?> delete(@PathVariable("priorityId") Long id);
}