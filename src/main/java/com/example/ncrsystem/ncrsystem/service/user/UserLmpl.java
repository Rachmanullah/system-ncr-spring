package com.example.ncrsystem.ncrsystem.service.user;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.UserMapper;
import com.example.ncrsystem.ncrsystem.common.util.PasswordHashing;
import com.example.ncrsystem.ncrsystem.dto.user.UserRequest;
import com.example.ncrsystem.ncrsystem.dto.user.UserResponse;
import com.example.ncrsystem.ncrsystem.model.Department;
import com.example.ncrsystem.ncrsystem.model.Role;
import com.example.ncrsystem.ncrsystem.model.User;
import com.example.ncrsystem.ncrsystem.repository.DepartmentRepository;
import com.example.ncrsystem.ncrsystem.repository.RoleRepository;
import com.example.ncrsystem.ncrsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserLmpl implements UserService{
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserLmpl(UserRepository userRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        Department department = departmentRepository.findById(userRequest.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));
        Role role = roleRepository.findById(userRequest.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        User user = userMapper.toEntity(userRequest, department, role);
        user.setPassword(PasswordHashing.hash(userRequest.getPassword()));
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse update(BigInteger userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Department department = departmentRepository.findById(userRequest.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));
        Role role = roleRepository.findById(userRequest.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        userMapper.updateEntity(user, userRequest, department, role);
        if (userRequest.getPassword() != null && !userRequest.getPassword().isBlank()) {
            user.setPassword(PasswordHashing.hash(userRequest.getPassword()));
        }
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse delete(BigInteger userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setDeleted(StatusConstant.DELETED);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }
}
