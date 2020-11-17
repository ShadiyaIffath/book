package com.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDto {

    private String dateReserved;

    private Date dateCreated = new Date();

    private String dateExpected;

    private String status = "Created";

    private int bookId;

    private int accountId;
}
