package com.example.ncrsystem.ncrsystem.dto.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MenuRequest {
    @NotBlank(message = "Menu Title Required")
    @NotNull(message = "Menu Title Required")
    @Length(max = 30)
    private String menuTitle;
    @Length(max = 30)
    private String menuIcon;
    @Length(max = 50)
    private String menuRoute;
    @Min(0)
    private Integer menuParentId;
}
