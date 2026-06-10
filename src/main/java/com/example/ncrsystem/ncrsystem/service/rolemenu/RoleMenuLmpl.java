package com.example.ncrsystem.ncrsystem.service.rolemenu;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.RoleMenuMapper;
import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuRequest;
import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuResponse;
import com.example.ncrsystem.ncrsystem.model.Menu;
import com.example.ncrsystem.ncrsystem.model.Role;
import com.example.ncrsystem.ncrsystem.model.RoleMenu;
import com.example.ncrsystem.ncrsystem.repository.MenuRepository;
import com.example.ncrsystem.ncrsystem.repository.RoleMenuRepository;
import com.example.ncrsystem.ncrsystem.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class RoleMenuLmpl implements RoleMenuService{
    private final RoleMenuRepository roleMenuRepository;
    private final RoleMenuMapper roleMenuMapper;
    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;

    public RoleMenuLmpl(RoleMenuRepository roleMenuRepository, RoleMenuMapper roleMenuMapper, RoleRepository roleRepository, MenuRepository menuRepository) {
        this.roleMenuRepository = roleMenuRepository;
        this.roleMenuMapper = roleMenuMapper;
        this.roleRepository = roleRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<RoleMenuResponse> findAll() {
        return roleMenuRepository.findAllRoleMenu()
                .stream()
                .map(roleMenuMapper::toResponse)
                .toList();
    }

    @Override
    public List<RoleMenuResponse> findByRoleId(BigInteger roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMenuRepository.findRoleMenuByRoleId(roleId)
                .stream()
                .map(roleMenuMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public RoleMenuResponse create(RoleMenuRequest roleMenuRequest) {
        Role role = roleRepository.findById(roleMenuRequest.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        Menu menu = menuRepository.findById(roleMenuRequest.getMenuId()).orElseThrow(() -> new RuntimeException("Menu not found"));

        RoleMenu roleMenu = roleMenuMapper.toEntity(roleMenuRequest, role, menu);
        RoleMenu roleMenuSave = roleMenuRepository.save(roleMenu);
        return roleMenuMapper.toResponse(roleMenuSave);
    }

    @Override
    @Transactional
    public RoleMenuResponse update(BigInteger roleMenuId, RoleMenuRequest roleMenuRequest) {
        RoleMenu roleMenu = roleMenuRepository.findById(roleMenuId).orElseThrow(() -> new RuntimeException("Role Menu not found"));
        Role role = roleRepository.findById(roleMenuRequest.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        Menu menu = menuRepository.findById(roleMenuRequest.getMenuId()).orElseThrow(() -> new RuntimeException("Menu not found"));
        roleMenu.setRole(role);
        roleMenu.setMenu(menu);
        roleMenu.setStatus(BigInteger.valueOf(roleMenuRequest.getStatus()));
        RoleMenu roleMenuSave = roleMenuRepository.save(roleMenu);

        return roleMenuMapper.toResponse(roleMenuSave);
    }

    @Override
    @Transactional
    public RoleMenuResponse delete(BigInteger roleMenuId) {
        RoleMenu roleMenu = roleMenuRepository.findById(roleMenuId).orElseThrow(() -> new RuntimeException("Role Menu not found"));
        roleMenu.setDeleted(StatusConstant.DELETED);

        RoleMenu roleMenuSave = roleMenuRepository.save(roleMenu);
        return roleMenuMapper.toResponse(roleMenuSave);
    }
}
