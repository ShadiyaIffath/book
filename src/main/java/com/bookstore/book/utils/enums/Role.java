package com.bookstore.book.utils.enums;

public enum Role {
    RXA("ROLE_ADMIN"),
    RXS("ROLE_USER");

    private String role;
    Role(String code) {
        this.role= code;
    }

    public String getRole(){
        return role;
    }
}
