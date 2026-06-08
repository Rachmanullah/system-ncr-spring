package com.example.ncrsystem.ncrsystem.service.menu;

import com.example.ncrsystem.ncrsystem.dto.menu.MenuRequest;
import com.example.ncrsystem.ncrsystem.dto.menu.MenuResponse;

import java.math.BigInteger;
import java.util.List;

public interface MenuService {
    List<MenuResponse> findAll();
    MenuResponse create(MenuRequest menuRequest);
    MenuResponse update(BigInteger menuId, MenuRequest menuRequest);
    MenuResponse delete(BigInteger menuId);
}
