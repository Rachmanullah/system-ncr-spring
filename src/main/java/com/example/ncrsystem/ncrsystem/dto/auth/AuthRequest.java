package com.example.ncrsystem.ncrsystem.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Username Required")
    private String username;
    @NotBlank(message = "Password Required")
    private String password;
}
