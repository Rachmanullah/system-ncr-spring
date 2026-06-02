package com.example.ncrsystem.ncrsystem.service.user;

import com.example.ncrsystem.ncrsystem.dto.user.UserRequest;
import com.example.ncrsystem.ncrsystem.dto.user.UserResponse;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse create(UserRequest userRequest);
    UserResponse update(BigInteger userId,UserRequest userRequest);
    UserResponse delete(BigInteger userId);
}
