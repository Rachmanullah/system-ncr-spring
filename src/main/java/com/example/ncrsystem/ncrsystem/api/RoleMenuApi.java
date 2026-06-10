package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/master/settings/rolemenu")
public interface RoleMenuApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @GetMapping("/{roleId}")
    ResponseEntity<?> findByRoleId(@PathVariable("roleId") Long roleId);
    @PostMapping
    ResponseEntity<?> create(@RequestBody RoleMenuRequest request);
    @PutMapping("/{roleMenuId}")
    ResponseEntity<?> update(@PathVariable("roleMenuId") Long id, @RequestBody RoleMenuRequest request);
    @DeleteMapping("/{roleMenuId}")
    ResponseEntity<?> delete(@PathVariable("roleMenuId") Long id);
}
