package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDto {
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String password;
    private boolean active = true;
}
