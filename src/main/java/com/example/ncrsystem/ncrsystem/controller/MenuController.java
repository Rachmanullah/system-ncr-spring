package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.MenuApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.menu.MenuRequest;
import com.example.ncrsystem.ncrsystem.service.menu.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class MenuController implements MenuApi {
    public final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(menuService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody MenuRequest request) {
        return ResponseHandler.success(menuService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id, @Valid @RequestBody MenuRequest request) {
        return ResponseHandler.success(menuService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(menuService.delete(BigInteger.valueOf(id)));
    }
}
