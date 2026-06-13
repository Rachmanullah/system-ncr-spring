package com.example.ncrsystem.ncrsystem.service.menu;


import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.MenuMapper;
import com.example.ncrsystem.ncrsystem.dto.menu.MenuRequest;
import com.example.ncrsystem.ncrsystem.dto.menu.MenuResponse;
import com.example.ncrsystem.ncrsystem.model.Menu;
import com.example.ncrsystem.ncrsystem.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class MenuLmpl implements MenuService{
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public MenuLmpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public List<MenuResponse> findAll() {
        return menuRepository.findAllMenu()
                .stream()
                .map(menu -> {
                    MenuResponse response = menuMapper.toResponse(menu);

                    if (menu.getMenuParentId() != null) {
                        menuRepository.findById(BigInteger.valueOf(menu.getMenuParentId()))
                                .ifPresent(parent ->
                                        menuMapper.toResponse(response, parent));
                    }

                    return response;
                })
                .toList();
    }

    @Override
    public MenuResponse create(MenuRequest menuRequest) {
        if(menuRequest.getMenuParentId() != null && menuRequest.getMenuParentId() != 0){
            menuRepository.findById(BigInteger.valueOf(menuRequest.getMenuParentId()))
                    .orElseThrow(()-> new RuntimeException("Parent Menu not found"));
        }
        Menu menu = menuMapper.toEntity(menuRequest);
        Menu menuSave = menuRepository.save(menu);
        return menuMapper.toResponse(menuSave);
    }

    @Override
    public MenuResponse update(BigInteger menuId, MenuRequest menuRequest) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu Not Found"));
        if(menuRequest.getMenuParentId() != null){
            menuRepository.findById(BigInteger.valueOf(menuRequest.getMenuParentId()))
                    .orElseThrow(()-> new RuntimeException("Parent Menu not found"));
        }
        menu.setMenuTitle(menuRequest.getMenuTitle());
        menu.setMenuIcon(menuRequest.getMenuIcon());
        menu.setMenuRoute(menuRequest.getMenuRoute());
        menu.setMenuParentId(menuRequest.getMenuParentId());

        Menu menuUpdated = menuRepository.save(menu);
        return menuMapper.toResponse(menuUpdated);
    }

    @Override
    public MenuResponse delete(BigInteger menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu Not Found"));
        menu.setDeleted(StatusConstant.DELETED);
        Menu menuDeleted = menuRepository.save(menu);
        return menuMapper.toResponse(menuDeleted);
    }
}
