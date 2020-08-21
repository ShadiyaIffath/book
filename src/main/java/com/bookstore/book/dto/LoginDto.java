package com.bookstore.book.dto;

public class LoginDto {
    private String email;
    private String pasword;

    public LoginDto(String email, String pasword) {
        this.email = email;
        this.pasword = pasword;
    }
    public LoginDto(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
