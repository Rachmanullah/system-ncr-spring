package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/inbox")
public interface InboxApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping("/approve")
    ResponseEntity<?> approveNcr(@RequestBody NCRRequestDto ncrRequest);
    @PostMapping("/reject")
    ResponseEntity<?> rejectNcr(@RequestBody NCRRequestDto ncrRequest);
}
