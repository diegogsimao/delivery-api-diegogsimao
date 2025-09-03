package com.deliverytech.delivery.entity.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    DELIVERY("DELIVERY"),
    RESTAURANT("RESTAURANT");

    private String role;

    UserRole(String _role) {
        this.role = _role;
    }

    public String getRole() {
        return role;
    }
}
