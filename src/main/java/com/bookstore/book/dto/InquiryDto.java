package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
    private int id;
    private String name;
    private String email;
    private int phone;
    private String question;
    private boolean responded;
}
