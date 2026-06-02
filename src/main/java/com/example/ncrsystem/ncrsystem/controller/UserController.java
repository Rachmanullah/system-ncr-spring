package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.UserApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.user.UserRequest;
import com.example.ncrsystem.ncrsystem.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(userService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest request) {
        return ResponseHandler.success(userService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id,@Valid @RequestBody UserRequest request) {
        return ResponseHandler.success(userService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(userService.delete(BigInteger.valueOf(id)));
    }
}
