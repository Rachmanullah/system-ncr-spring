package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.user.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
public interface UserApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@RequestBody UserRequest request);
    @PutMapping("/{userId}")
    ResponseEntity<?> update(@PathVariable("userId") Long id, @RequestBody UserRequest request);
    @DeleteMapping("/{userId}")
    ResponseEntity<?> delete(@PathVariable("userId") Long id);
}
