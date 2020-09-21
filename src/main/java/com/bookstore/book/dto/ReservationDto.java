package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private int id;
    private String status;
    private String dateReserved;
    private String dateCreated;
    private String dateReturned;
    private String dateExpected;
    private BookDto book;
    private AccountDto account;

}
