package com.kodilla.ecommercee.enums;

public enum Permissions {
    P_USER("user"),
    P_ADMIN("admin"),
    P_ROOT("root");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
