package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDtoForAndroid {
    private int id;
    private String status;
    private Date dateReserved;
    private Date dateCreated;
    private Date dateReturned;
    private Date dateExpected;
    private int bookId;
    private int accountId;
    private BookDtoForAndroid bookDto;
    private AccountDto accountDto;
}
