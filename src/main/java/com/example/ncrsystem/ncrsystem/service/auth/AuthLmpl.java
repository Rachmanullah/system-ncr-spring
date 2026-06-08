package com.example.ncrsystem.ncrsystem.service.auth;

import com.example.ncrsystem.ncrsystem.common.mapper.AuthMapper;
import com.example.ncrsystem.ncrsystem.common.util.JwtService;
import com.example.ncrsystem.ncrsystem.common.util.PasswordHashing;
import com.example.ncrsystem.ncrsystem.dto.auth.AuthRequest;
import com.example.ncrsystem.ncrsystem.dto.auth.AuthResponse;
import com.example.ncrsystem.ncrsystem.model.User;
import com.example.ncrsystem.ncrsystem.repository.AuthRepository;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class AuthLmpl implements AuthService{
    public final AuthRepository authRepository;
    private final JwtService jwtService;
    private final AuthMapper authMapper;
    public AuthLmpl(AuthRepository authRepository, JwtService jwtService, AuthMapper authMapper) {
        this.authRepository = authRepository;
        this.jwtService = jwtService;
        this.authMapper = authMapper;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        User user = authRepository.findByUsername(authRequest.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
        if (!PasswordHashing.matches(
                authRequest.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);
        AuthResponse response = authMapper.toResponse(user);
        response.setToken(token);
        Claims claims = jwtService.extractAllClaims(token);
        System.out.println("Token Payload : "+ claims);
        return response;
    }
}
