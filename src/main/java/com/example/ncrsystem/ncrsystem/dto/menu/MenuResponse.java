package com.example.ncrsystem.ncrsystem.dto.menu;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonPropertyOrder({
        "menuId",
        "menuTitle",
        "menuIcon",
        "menuRoute",
        "menuParentId",
        "menuParentName",
        "menuParentIcon"
})
public class MenuResponse {
    private BigInteger menuId;
    private String menuTitle;
    private String menuIcon;
    private String menuRoute;
    private Integer menuParentId;
    private String menuParentTitle;
    private String menuParentIcon;
}
