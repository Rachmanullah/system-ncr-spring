package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.menu.MenuRequest;
import com.example.ncrsystem.ncrsystem.dto.menu.MenuResponse;
import com.example.ncrsystem.ncrsystem.model.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {
    public Menu toEntity(MenuRequest menuRequest){
        Menu entity = new Menu();
        entity.setMenuTitle(menuRequest.getMenuTitle());
        entity.setMenuIcon(menuRequest.getMenuIcon());
        entity.setMenuRoute(menuRequest.getMenuRoute());
        entity.setMenuParentId(menuRequest.getMenuParentId());

        return entity;
    }

    public MenuResponse toResponse(Menu menuEntity){
        MenuResponse response = new MenuResponse();
        response.setMenuId(menuEntity.getMenuId());
        response.setMenuTitle(menuEntity.getMenuTitle());
        response.setMenuIcon(menuEntity.getMenuIcon());
        response.setMenuRoute(menuEntity.getMenuRoute());
        response.setMenuParentId(menuEntity.getMenuParentId());
        return response;
    }

    public MenuResponse toResponse(MenuResponse menuResponse, Menu menuParent){
        menuResponse.setMenuParentTitle(menuParent.getMenuTitle());
        menuResponse.setMenuParentIcon(menuParent.getMenuIcon());
        return menuResponse;
    }
}
