package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.user.UserRequest;
import com.example.ncrsystem.ncrsystem.dto.user.UserResponse;
import com.example.ncrsystem.ncrsystem.model.Department;
import com.example.ncrsystem.ncrsystem.model.Role;
import com.example.ncrsystem.ncrsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final DepartmentMapper departmentMapper;
    private final RoleMapper roleMapper;

    public UserMapper(DepartmentMapper departmentMapper, RoleMapper roleMapper) {
        this.departmentMapper = departmentMapper;
        this.roleMapper = roleMapper;
    }

    public User toEntity(UserRequest request, Department department, Role role) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setStatus(request.getStatus());
        user.setDepartment(department);
        user.setRole(role);
        return user;
    }
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setFullname(user.getFullname());
        response.setEmail(user.getEmail());
        response.setStatus(user.getStatus());
        response.setDepartment(departmentMapper.toResponse(user.getDepartment()));
        response.setRole(roleMapper.toResponse(user.getRole()));
        return response;
    }

    public void updateEntity(User entity, UserRequest request, Department department, Role role) {
        entity.setUsername(request.getUsername());
        entity.setFullname(request.getFullname());
        entity.setEmail(request.getEmail());
        entity.setStatus(request.getStatus());
        entity.setDepartment(department);
        entity.setRole(role);
    }
}
