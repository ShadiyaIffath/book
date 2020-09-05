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
public class CreateReservationDto {
    private Date dateReserved;

    private Date dateCreated = new Date();

    private String status = "Created";

    private int accountId;

    private int bookId;


}
