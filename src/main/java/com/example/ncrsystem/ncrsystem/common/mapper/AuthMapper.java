package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.auth.AuthResponse;
import com.example.ncrsystem.ncrsystem.model.User;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class AuthMapper {
    public AuthResponse toResponse(User user){
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(user.getUserId());
        authResponse.setUsername(user.getUsername());
        return  authResponse;
    }
}
