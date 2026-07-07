package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.user.UserRequest;
import com.example.ncrsystem.ncrsystem.validation.CreateUserValidation;
import com.example.ncrsystem.ncrsystem.validation.UpdateUserValidation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/system/users")
public interface UserApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@Validated(CreateUserValidation.class) @RequestBody UserRequest request);
    @PutMapping("/{userId}")
    ResponseEntity<?> update(@PathVariable("userId") Long id,@Validated(UpdateUserValidation.class) @RequestBody UserRequest request);
    @DeleteMapping("/{userId}")
    ResponseEntity<?> delete(@PathVariable("userId") Long id);
}
