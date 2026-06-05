package com.example.ncrsystem.ncrsystem.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Data
public class UserRequest {
    @NotBlank(message = "Username Required")
    @Length(max=50)
    private String username;
    @NotBlank(message = "Password Required")
    private String password;
    @NotBlank(message = "Fullname Required")
    @Length(max=150)
    private String fullname;
    @Email(message = "Format email not valid")
    @Length(max=100)
    private String email;
    @NotNull(message = "Department must be selected")
    @Min(1)
    private BigInteger departmentId;
    private String position;
    @NotNull(message = "Role must be selected")
    @Min(1)
    private BigInteger roleId;
    private BigInteger status;
    private BigInteger deleted;
}
