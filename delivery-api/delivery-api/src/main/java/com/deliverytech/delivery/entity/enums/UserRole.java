package com.deliverytech.delivery.entity.enums;

public enum UserRole {
    ADMIN("Admin"),
    CUSTOMER("Customer"),
    DELIVERY("Delivery"),
    RESTAURANT("Restaurant");

    private String role;

    UserRole(String _role) {
        this.role = _role;
    }

    public String getRole() {
        return role;
    }
}
