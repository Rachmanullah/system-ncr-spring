package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuRequest;
import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuResponse;
import com.example.ncrsystem.ncrsystem.model.Menu;
import com.example.ncrsystem.ncrsystem.model.Role;
import com.example.ncrsystem.ncrsystem.model.RoleMenu;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class RoleMenuMapper {
    public RoleMenu toEntity(RoleMenuRequest request, Role role, Menu menu) {
        return RoleMenu.builder()
                .role(role)
                .menu(menu)
                .status(
                        request.getStatus() == null
                                ? BigInteger.ZERO
                                : BigInteger.valueOf(request.getStatus())
                )
                .build();
    }

    public RoleMenuResponse toResponse(RoleMenu entity) {
        RoleMenuResponse response = new RoleMenuResponse();
        response.setRoleMenuId(entity.getRoleMenuId());

        if (entity.getRole() != null) {
            response.setRoleId(entity.getRole().getRoleId());
            response.setRoleName(entity.getRole().getRoleName());
        }

        if (entity.getMenu() != null) {
            response.setMenuId(entity.getMenu().getMenuId());
            response.setMenuTitle(entity.getMenu().getMenuTitle());
        }

        response.setStatus(entity.getStatus());
        return response;
    }
}
