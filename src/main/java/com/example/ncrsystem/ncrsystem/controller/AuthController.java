package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.AuthApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.auth.AuthRequest;
import com.example.ncrsystem.ncrsystem.service.auth.AuthService;
import jakarta.validation.Valid;
import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseHandler.success(authService.login(authRequest));
    }
}
