package com.example.ncrsystem.ncrsystem.service.auth;

import com.example.ncrsystem.ncrsystem.dto.auth.AuthRequest;
import com.example.ncrsystem.ncrsystem.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
}
