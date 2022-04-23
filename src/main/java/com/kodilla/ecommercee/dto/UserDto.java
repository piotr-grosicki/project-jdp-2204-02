package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.enums.AppRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private AppRoles role;
}